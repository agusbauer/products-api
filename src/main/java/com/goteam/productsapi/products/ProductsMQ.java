package com.goteam.productsapi.products;

import com.goteam.productsapi.products.models.Product;
import org.apache.pulsar.client.api.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProductsMQ {


    private PulsarClient client;
    private Producer<byte[]> producer;
    private Consumer consumer1;
    private Consumer consumer2;

    public ProductsMQ(){

        try {
            client = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650")
                    .build();
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }

        try {
            producer = client.newProducer()
                    .topic("go-products-topic")
                    .create();

            consumer1 = client.newConsumer()
                    .topic("go-products-topic")
                    .subscriptionName("sub")
                    .subscriptionType(SubscriptionType.Shared)
                    .subscribe();

            consumer2 = client.newConsumer()
                    .topic("go-products-topic")
                    .subscriptionName("sub")
                    .subscriptionType(SubscriptionType.Shared)
                    .subscribe();
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);

            while (true) {
                // Wait for a message
                Message msg = consumer1.receive();

                try {
                    // Do something with the message
                    System.out.printf("Message received in c1: %s", new String(msg.getData()));
                    System.out.println();

                    // Acknowledge the message so that it can be deleted by the message broker
                    consumer1.acknowledge(msg);
                } catch (Exception e) {
                    // Message failed to process, redeliver later
                    consumer1.negativeAcknowledge(msg);
                }
            }
        });

        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        executor2.submit(() -> {

            String threadName = Thread.currentThread().getName();
            System.out.println("Hello2 " + threadName);

            while (true) {
                // Wait for a message
                Message msg = consumer2.receive();

                try {
                    // Do something with the message
                    System.out.printf("Message received in c2: %s", new String(msg.getData()));
                    System.out.println();

                    // Acknowledge the message so that it can be deleted by the message broker
                    consumer2.acknowledge(msg);
                } catch (Exception e) {
                    // Message failed to process, redeliver later
                    consumer2.negativeAcknowledge(msg);
                }
            }
        });

    }

    public boolean sendMessage(Product product){
        try {
            producer.send(product.toString().getBytes());
        } catch (PulsarClientException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }



}

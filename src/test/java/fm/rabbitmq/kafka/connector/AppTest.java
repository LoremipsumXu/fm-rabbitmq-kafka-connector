/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package fm.rabbitmq.kafka.connector;

import org.junit.Test;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.GreeterGrpc;
import io.grpc.examples.GreeterOuterClass.HelloReply;
import io.grpc.examples.GreeterOuterClass.HelloRequest;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

public class AppTest {
    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
    @Test
    public void testGetMessage(){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1",6565)
                .usePlaintext()
                .build();

        GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName("gggg").build();
        HelloReply response;
        response = blockingStub.sayHello(request);
        System.out.println(response.getMessage());

        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

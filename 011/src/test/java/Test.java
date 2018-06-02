import ru.spbau.grpcmessenger.Client;
import ru.spbau.grpcmessenger.Handler;
import ru.spbau.grpcmessenger.Message;
import ru.spbau.grpcmessenger.Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class Test {
    @org.junit.Test
    public void test() throws InterruptedException {
        Client clientA = new Client("localhost", 14816);
        Server serverA = new Server(14817);
        ByteArrayOutputStream outputStreamA = new ByteArrayOutputStream();
        Client clientB = new Client("localhost", 14817);
        Server serverB = new Server(14816);
        ByteArrayOutputStream outputStreamB = new ByteArrayOutputStream();
        Message messageAToB = Message.newBuilder().setContent("A --> B").setSender("A").setTimestamp(1).build();
        Message messageBToA = Message.newBuilder().setContent("B --> A").setSender("B").setTimestamp(1).build();
        Thread serverBThread = new Thread(() -> serverB.run(x -> {
            try {
                outputStreamB.write(x.toString().getBytes());
            } catch (IOException e) {
                fail();
            }
        }));
        serverBThread.start();
        Thread serverAThread = new Thread(() -> serverA.run(x -> {
            try {
                outputStreamA.write(x.toString().getBytes());
            } catch (IOException e) {
                fail();
            }
        }));
        serverAThread.start();
        Thread.sleep(1000);
        new Thread(clientA::run).start();
        Thread.sleep(1000);
        clientA.sendMessage(messageAToB);
        new Thread(clientB::run).start();
        Thread.sleep(1000);
        clientB.sendMessage(messageBToA);
        Thread.sleep(1000);
        assertEquals(messageAToB.toString(), new String(outputStreamB.toByteArray()));
        assertEquals(messageBToA.toString(), new String(outputStreamA.toByteArray()));
    }
}

package ru.spbau.grpcmessenger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * Something like UI
 */
public class Handler {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
   private final Scanner scanner;
    private final PrintWriter writer;
    private final Client client;
    private final String sender;
    private final Server server;
    private final CountDownLatch latch;

    public Handler(InputStream inputStream, OutputStream outputStream, Client client, String sender, Server server) {
        scanner = new Scanner(inputStream);
        writer = new PrintWriter(new OutputStreamWriter(outputStream), true);
        this.client = client;
        this.sender = sender;
        this.server = server;
        latch = new CountDownLatch(1);
    }

    /**
     * Runs client: reads line and send it.
     * Exits if user prints 'exit' and confirms it by printing 'yes'.
     * When exits, reduces 'latch' counter in order to let the server-side know that it is time to close.
     */
    public void runClient() {
        client.run();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                writer.println("'yes' to exit");
                if (scanner.nextLine().equals("yes")) {
                    client.shutdown();
                    latch.countDown();
                    break;
                }
            }
            Message message = Message.newBuilder()
                    .setContent(line)
                    .setSender(sender)
                    .setTimestamp(System.currentTimeMillis())
                    .build();
            client.sendMessage(message);
            showMessage(message);
        }
    }

    private void showMessage(Message message) {
        writer.println("[" + dateFormat.format(new Date(message.getTimestamp())) + "] " +
                message.getSender() + ": " +
                message.getContent()
        );
    }

    /**
     * Runs server. Closes when 'latch' is dropped.
     */
    public void runServer() {
        server.run(this::showMessage);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.stop();
    }
}

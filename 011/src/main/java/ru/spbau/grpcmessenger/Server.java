package ru.spbau.grpcmessenger;

import io.grpc.ServerBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Server
 */
public class Server {
    private static final Log LOGGER = LogFactory.getLog(Server.class);
    private final int port;
    private io.grpc.Server server;

    public Server(int port) {
        this.port = port;
    }

    /**
     * Runs the server-side service
     * @param messageConsumer Message consumer
     */
    public void run(Consumer<Message> messageConsumer) {

        if (LOGGER != null) {
            LOGGER.info(port + ": run");
        }
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        try {
            MessengerImpl messenger = new MessengerImpl(messageConsumer);
            server = serverBuilder.addService(messenger).build().start();
            if (LOGGER != null) {
                LOGGER.info(port + ": service started");
            }
        } catch (IOException e) {
            if (LOGGER != null) {
                LOGGER.warn(port + ": thrown during service starting: " + e.getClass() + ": " + e.getMessage());
            }
        }
        if (LOGGER != null) {
            LOGGER.info(port + ": finished run");
        }
    }

    /**
     * Shutdowns the server
     */
    public void stop() {
        if (LOGGER != null) {
            LOGGER.info(port + ": stopping");
        }
        if (server != null) {
            server.shutdown();
        }
        if (LOGGER != null) {
            LOGGER.info(port + ": stopped");
        }
    }
}
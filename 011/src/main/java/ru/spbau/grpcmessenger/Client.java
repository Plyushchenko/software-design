package ru.spbau.grpcmessenger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Client
 */
public class Client {
    private final static Log LOGGER = LogFactory.getLog(Client.class);

    private final String host;
    private final int port;
    private final ManagedChannel channel;
    private StreamObserver<Message> responseObserver;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }

    /**
     * Some initialization.
     * @param messageConsumer Message consumer
     */
    public void run(Consumer<Message> messageConsumer) {
        if (LOGGER != null) {
            LOGGER.info(this.host + ":" + this.port + ": run");
        }
        MessengerGrpc.MessengerStub asyncStub = MessengerGrpc.newStub(channel);
        IncomingStreamObserver incomingStreamObserver = new IncomingStreamObserver(messageConsumer);
        responseObserver = asyncStub.runMessenger(incomingStreamObserver);
        if (LOGGER != null) {
            LOGGER.info(this.host + ":" + this.port + ": finished run");
        }

    }

    /**
     * Calls 'run' with dummy consumer.
     */
    public void run() {
        run(x -> {});
    }

    /**
     * Shutdown
     */
    public void shutdown() {
        try {
            if (LOGGER != null) {
                LOGGER.info(this.host + ":" + this.port + ": shutdown");
            }
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            if (LOGGER != null) {
                LOGGER.warn(
                        this.host + ":" + this.port + ": thrown during shutdown: "
                                + e.getClass() + ": " + e.getMessage()
                );
            }
        }
        if (LOGGER != null) {
            LOGGER.info(this.host + ":" + this.port + ": finished shutdown");
        }
    }

    public void sendMessage(Message message) {
        if (LOGGER != null) {
            LOGGER.info(this.host + ": " + this.port + ": sendMessage: " + message);
        }
        responseObserver.onNext(message);
        if (LOGGER != null) {
            LOGGER.info(this.host + ":" + this.port + ": finished sendMessage: " + message);
        }
    }
}

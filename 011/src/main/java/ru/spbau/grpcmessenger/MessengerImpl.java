package ru.spbau.grpcmessenger;

import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.function.Consumer;

/**
 * Messenger implementation
 */
public class MessengerImpl extends MessengerGrpc.MessengerImplBase {
    private final static Log LOGGER = LogFactory.getLog(MessengerImpl.class);
    private final Consumer<Message> messageConsumer;

    MessengerImpl(Consumer<Message> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    /**
     * Creates stream observer wrapper
     * @param ignored Ignored
     * @return Stream observer wrapper
     */
    @Override
    public StreamObserver<Message> runMessenger(StreamObserver<Message> ignored) {
        if (LOGGER != null) {
            LOGGER.info("runMessenger");
        }
        IncomingStreamObserver result = new IncomingStreamObserver(messageConsumer);
        if (LOGGER != null) {
            LOGGER.info("finished runMessenger");
        }
        return result;
    }
}
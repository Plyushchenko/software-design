package ru.spbau.grpcmessenger;

import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.function.Consumer;

/**
 * Simple wrapper of 'StreamObserver'
 */
public class IncomingStreamObserver implements StreamObserver<Message> {
    private final static Log LOGGER = LogFactory.getLog(IncomingStreamObserver.class);

    private final Consumer<Message> messageConsumer;

    IncomingStreamObserver(Consumer<Message> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    /**
     * Consumes the incoming message
     */
    @Override
    public void onNext(Message value) {
        if (LOGGER != null) {
            LOGGER.info("onNext: " + value);
        }
        messageConsumer.accept(value);
        if (LOGGER != null) {
            LOGGER.info("finished onNext: " + value);
        }

    }

    @Override
    public void onError(Throwable t) {
        if (LOGGER != null) {
            LOGGER.info("empty method onError: " + t.getMessage());
        }
    }

    @Override
    public void onCompleted() {
        if (LOGGER != null) {
            LOGGER.info("empty method onCompleted");
        }
    }
}

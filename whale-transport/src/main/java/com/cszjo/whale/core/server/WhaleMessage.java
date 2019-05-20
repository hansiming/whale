package com.cszjo.whale.core.server;

public class WhaleMessage {

    private long requestId;
    private boolean isRequest;
    private byte[] data;

    private WhaleMessage(Builder builder) {
        requestId = builder.requestId;
        isRequest = builder.isRequest;
        data = builder.data;
    }


    public static final class Builder {
        private long requestId;
        private boolean isRequest;
        private byte[] data;

        public Builder() {
        }

        public Builder requestId(long val) {
            requestId = val;
            return this;
        }

        public Builder isRequest(boolean val) {
            isRequest = val;
            return this;
        }

        public Builder data(byte[] val) {
            data = val;
            return this;
        }

        public WhaleMessage build() {
            return new WhaleMessage(this);
        }
    }

    public long getRequestId() {
        return requestId;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public byte[] getData() {
        return data;
    }
}

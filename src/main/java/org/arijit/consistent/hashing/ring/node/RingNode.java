package org.arijit.consistent.hashing.ring.node;

import org.arijit.consistent.hashing.handler.IRequestHandler;

public class RingNode<D> {
    private final String id;
    private final Long startRange;

    private final IRequestHandler<D> requestHandler;

    private RingNode(String id, Long startRange, IRequestHandler<D> requestHandler) {
        this.id = id;
        this.startRange = startRange;
        this.requestHandler = requestHandler;
    }

    public IRequestHandler<D> getRequestHandler() {
        return requestHandler;
    }

    public Long getStartRange() {
        return startRange;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RingNode{" +
                "id='" + id + '\'' +
                ", startRange=" + startRange +
                ", requestHandler=" + requestHandler +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder<D>{
        private String id;
        private Long startRange;
        private IRequestHandler<D> requestHandler;

        private Builder(){

        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setStartRange(Long key) {
            this.startRange = key;
            return this;
        }

        public Builder setRequestHandler(IRequestHandler<D> requestHandler) {
            this.requestHandler = requestHandler;
            return this;
        }

        public RingNode build(){
            return new RingNode(id, startRange, requestHandler);
        }
    }
}

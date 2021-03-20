package org.arijit.consistent.hashing.handler;


public interface IRequestHandler<V> {

    /*This method will handle the request. The functionlaity should be implemented by user of this framework*/
    public void handleRequest(V request);
}

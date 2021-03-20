package org.arijit.consistent.hashing.ring.node;

import org.arijit.consistent.hashing.handler.IRequestHandler;

import java.util.Comparator;

public interface IRingNode<K,V> extends Comparator<IRingNode<K,V>> {
        public String getID();
        public K getStartRange();
        /*this method will expose the actual handler funtion like what to do with request,
        * Ex: if this framework works as LB, it would route the request to proper server
        * If this framework is running in front of a DB cluster, it will upsert/insert records in DB node
        * Handler function should be implemented by user of this framework*/
        public IRequestHandler getRequestHandler();
}

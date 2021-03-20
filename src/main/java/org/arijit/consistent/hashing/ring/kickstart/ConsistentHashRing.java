package org.arijit.consistent.hashing.ring.kickstart;

import org.arijit.consistent.hashing.annotation.processor.AnnotationProcessor;
import org.arijit.consistent.hashing.context.RingContext;
import org.arijit.consistent.hashing.ring.node.RingNode;
import org.arijit.consistent.hashing.ring.topology.RingTopology;

import java.io.Serializable;
import java.util.SortedMap;

public class ConsistentHashRing {

    public static void configure(){

        AnnotationProcessor.getInstance().processAnnotation();
    }

    public static Router router(){
        return new Router();
    }

    public static class Router{
        private Serializable key;
        private Object data;

        private Router(){

        }

        public Router key(Serializable key){
            this.key = key;
            return this;
        }

        public Router data(Object data){
            this.data = data;
            return this;
        }

        public boolean route(){
            if(!RingContext.getInstance().isConsistentHashRingEnabled()){
                System.out.println("Consistent HashRing is not Enabled");
                return false;
            }
            SortedMap<Long, RingNode> ringMap = RingTopology.getInstance().getTopology();
            if(ringMap==null || ringMap.isEmpty()){
                System.out.println("No node is participating in Ring");
                return false;
            }

        try {
            byte[] keyBytes = RingContext.getInstance().getKeyEncoder().getBytes(key);
            long murmer3Hash = RingContext.getInstance().getHashAlgo().generateHash(keyBytes);
            SortedMap<Long, RingNode> tailMap = ringMap.tailMap(murmer3Hash);
            //here we are getting the nearest next node in clockwise direction for a request
            Long nodeId = tailMap.isEmpty()?ringMap.firstKey() :tailMap.firstKey();
            RingNode ringNode = ringMap.get(nodeId);
            System.out.println("Routing Details : key: "+key+" Hash: "+murmer3Hash+" choosen Node: "+ nodeId);
            ringNode.getRequestHandler().handleRequest(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return false;
        }
    }
}

package org.arijit.consistent.hashing.ring.topology;

import org.arijit.consistent.hashing.ring.node.RingNode;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class RingTopology {

    private SortedMap<Long, RingNode> ringMap;

    private RingTopology(){
        ringMap = new TreeMap<>();
    }

    public void addNodes(List<RingNode> nodes){
        if(nodes==null || nodes.isEmpty())
            return;
        nodes.stream().forEach(node->ringMap.put(node.getStartRange(),node));
    }

    public SortedMap<Long, RingNode> getTopology(){
        return ringMap;
    }
    public static RingTopology getInstance(){
        return RingTopologyInner.getInstance();
    }

    public static class RingTopologyInner{
        private static RingTopology instance = new RingTopology();
        private RingTopologyInner(){

        }

        public static RingTopology getInstance() {
            return instance;
        }
    }
}

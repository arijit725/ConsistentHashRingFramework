package org.test.annotation;

import org.arijit.consistent.hashing.annotation.model.RingConfiguration;
import org.arijit.consistent.hashing.ring.configurer.IRingConfigurer;
import org.arijit.consistent.hashing.ring.node.RingNode;

import java.util.ArrayList;
import java.util.List;

@RingConfiguration
public class FIleBasedRingConfigurer implements IRingConfigurer<String> {


    @Override
    public List<RingNode<String>> listNode() {

        List<RingNode<String>> ringNodeList = new ArrayList<>();
        RingNode node1 = RingNode.builder().setId("node1").setStartRange(1232323L).setRequestHandler((data)->{
            System.out.println("Handling request for node1..data: "+data);
        }).build();
        ringNodeList.add(node1);
        RingNode node2 = RingNode.builder().setId("node2").setStartRange(1234323L).setRequestHandler((data)->{
            System.out.println("Handling request for node1..data: "+data);
        }).build();
        ringNodeList.add(node2);
        return ringNodeList;
    }
}

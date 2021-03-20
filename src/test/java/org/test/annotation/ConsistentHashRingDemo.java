package org.test.annotation;

import org.arijit.consistent.hashing.annotation.model.EnableConsistentHashingRing;
import org.arijit.consistent.hashing.ring.kickstart.ConsistentHashRing;

@EnableConsistentHashingRing(basePackage = "org.test.annotation")
public class ConsistentHashRingDemo {

    public static void main(String args[]){
        ConsistentHashRing.configure();

        ConsistentHashRing.router().key("Test123").data("this is a test").route();
    }
}

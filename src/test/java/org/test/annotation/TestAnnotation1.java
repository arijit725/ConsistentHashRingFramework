package org.test.annotation;

import org.arijit.consistent.hashing.annotation.model.EnableConsistentHashingRing;
import org.arijit.consistent.hashing.encoder.StringEncoder;
import org.arijit.consistent.hashing.ring.kickstart.ConsistentHashRing;

@EnableConsistentHashingRing(basePackage = "org.test.annotation", keyEncoder = StringEncoder.class)
public class TestAnnotation1 {



    public static void main(String args[]){
        System.out.println("Inside TestAnnotation 1");
//        RingRunner.configure();
        TestAnnotation2 testAnnotation2 = new TestAnnotation2();
        testAnnotation2.test();

        ConsistentHashRing.router().key("test123").data("this is a test").route();
    }
}

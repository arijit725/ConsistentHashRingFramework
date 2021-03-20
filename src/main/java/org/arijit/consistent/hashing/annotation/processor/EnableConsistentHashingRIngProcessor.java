package org.arijit.consistent.hashing.annotation.processor;

import org.arijit.consistent.hashing.algo.IHashAlgo;
import org.arijit.consistent.hashing.annotation.model.EnableConsistentHashingRing;
import org.arijit.consistent.hashing.context.RingContext;
import org.arijit.consistent.hashing.encoder.IEncoder;

public class EnableConsistentHashingRIngProcessor {


    public String findBasePakage() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int count=0;
        String basePackage = null;
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=0; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            System.out.println("Calling className: "+ste.getClassName());
            Class<?> clz = Class.forName(ste.getClassName());
            EnableConsistentHashingRing markedAnotation = clz.getAnnotation(EnableConsistentHashingRing.class);

            if(markedAnotation!=null){
                System.out.println("Entry point for EnableConsistentHashingRing found class:"+clz.getName()+" basePackage: "+markedAnotation.basePackage());
                basePackage = markedAnotation.basePackage();
                Class<?> encoder = markedAnotation.keyEncoder();
                IEncoder encoderObj = (IEncoder) encoder.newInstance();
                RingContext.getInstance().setKeyEncoder(encoderObj);
                Class<?> hashAlgoClass = markedAnotation.hashAlgo();
                IHashAlgo hashAlgo = (IHashAlgo) hashAlgoClass.newInstance();
                RingContext.getInstance().setHashAlgo(hashAlgo);
                count++;
            }

        }
        if(count>1){
            throw new RuntimeException("Multiple class is annotated with @EnableConsistentHashingRing");
        }

        return basePackage;
    }
}

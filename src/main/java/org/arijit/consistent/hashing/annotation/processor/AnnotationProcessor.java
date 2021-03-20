package org.arijit.consistent.hashing.annotation.processor;

import org.arijit.consistent.hashing.context.RingContext;

public class AnnotationProcessor {

    public void processAnnotation(){
        EnableConsistentHashingRIngProcessor consistentHashingRIngProcessor = new EnableConsistentHashingRIngProcessor();
        try {
           String basePackage =  consistentHashingRIngProcessor.findBasePakage();
           if(basePackage==null) {
               System.out.println("No class is annotated with @EnableConsistentHashingRing..hence consistent hashring is not activated");
                RingContext.getInstance().setConsistentHashRingEnabled(false);
               return;
           }
            RingContext.getInstance().setConsistentHashRingEnabled(true);
            RingConfigurerProcessor ringConfigurerProcessor = new RingConfigurerProcessor(basePackage);
            ringConfigurerProcessor.processRingConfigurer();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    public static AnnotationProcessor getInstance(){
        return AnnotationProcessorInner.getInstance();
    }


    public static class AnnotationProcessorInner{
        private static AnnotationProcessor instance = new AnnotationProcessor();

        public static AnnotationProcessor getInstance() {
            return instance;
        }
    }
}

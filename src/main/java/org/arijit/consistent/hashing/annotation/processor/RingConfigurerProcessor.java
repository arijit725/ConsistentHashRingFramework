package org.arijit.consistent.hashing.annotation.processor;

import org.arijit.consistent.hashing.annotation.model.RingConfiguration;
import org.arijit.consistent.hashing.context.RingContext;
import org.arijit.consistent.hashing.ring.configurer.IRingConfigurer;
import org.arijit.consistent.hashing.ring.node.RingNode;
import org.arijit.consistent.hashing.ring.topology.RingTopology;

import java.util.Iterator;
import java.util.List;

public class RingConfigurerProcessor {

    private String basePackage;
    protected RingConfigurerProcessor(String basePackage){
        this.basePackage = basePackage;
    }

    public void processRingConfigurer() throws IllegalAccessException, InstantiationException {
        List<Class> annotatedClass = AnnotationProcessorUtil.getInstance().findAnnotatedClass(basePackage, RingConfiguration.class);
        if(annotatedClass.isEmpty())
            return;
        Iterator<Class> it = annotatedClass.iterator();
        while (it.hasNext()){
            Class<IRingConfigurer> cls = it.next();

            IRingConfigurer obj = cls.newInstance();
            List<RingNode> nodeList = obj.listNode();
            System.out.println("Registered Nodes in ring: "+nodeList);
            //need to chcek for duplicacy here
            RingContext.getInstance().getRingTopology().addNodes(nodeList);

        }
        System.out.println("Ring topology: "+ RingTopology.getInstance().getTopology());
    }
}

package org.arijit.consistent.hashing.ring.configurer;

import org.arijit.consistent.hashing.ring.node.RingNode;

import java.util.List;

public interface IRingConfigurer<D> {

    public List<RingNode<D>> listNode();

}

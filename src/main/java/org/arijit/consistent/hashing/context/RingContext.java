package org.arijit.consistent.hashing.context;

import org.arijit.consistent.hashing.algo.IHashAlgo;
import org.arijit.consistent.hashing.encoder.IEncoder;
import org.arijit.consistent.hashing.ring.topology.RingTopology;

import java.io.Serializable;

public class RingContext {

    private RingTopology ringTopology;

    private IEncoder<Serializable> keyEncoder;

    private IHashAlgo hashAlgo;

    public boolean consistentHashRingEnabled;

    private  RingContext(){
        ringTopology = RingTopology.getInstance();
    }

    public void setKeyEncoder(IEncoder<Serializable> keyEncoder) {
        this.keyEncoder = keyEncoder;
    }

    public void setHashAlgo(IHashAlgo hashAlgo) {
        this.hashAlgo = hashAlgo;
    }

    public IHashAlgo getHashAlgo() {
        return hashAlgo;
    }

    public IEncoder<Serializable> getKeyEncoder() {
        return keyEncoder;
    }

    public static RingContext getInstance(){
        return RingContextInner.getInstance();
    }

    public RingTopology getRingTopology() {
        return ringTopology;
    }

    public void setConsistentHashRingEnabled(boolean consistentHashRingEnabled) {
        this.consistentHashRingEnabled = consistentHashRingEnabled;
    }

    public boolean isConsistentHashRingEnabled() {
        return consistentHashRingEnabled;
    }

    private static class RingContextInner{
        private static RingContext instance = new RingContext();
        private RingContextInner(){

        }

        public static RingContext getInstance() {
            return instance;
        }
    }
}

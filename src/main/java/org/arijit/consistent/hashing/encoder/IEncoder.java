package org.arijit.consistent.hashing.encoder;

import java.io.Serializable;

public interface IEncoder<T extends Serializable> {


    byte[] getBytes(T key);
}

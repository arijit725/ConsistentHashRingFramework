package org.arijit.consistent.hashing.encoder;

public class StringEncoder implements IEncoder<String> {
    @Override
    public byte[] getBytes(String data) {
        return data.getBytes();
    }
}

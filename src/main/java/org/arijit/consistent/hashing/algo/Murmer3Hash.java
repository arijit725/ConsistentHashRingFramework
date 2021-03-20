package org.arijit.consistent.hashing.algo;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Murmer3Hash implements IHashAlgo {

    HashFunction hashFunction = Hashing.murmur3_128();
    Hasher hasher = hashFunction.newHasher();

    @Override
    public long generateHash(byte[] key) throws IOException {
        ByteArrayOutputStream out=null;
        ObjectOutputStream bout = null;
        try {

            out = new ByteArrayOutputStream();
            bout = new ObjectOutputStream(out);
            bout.writeObject(key);
            bout.flush();
            HashCode hashCode = hashFunction.hashBytes(out.toByteArray());
            long hash = hashCode.asLong();
            if(hash<0)
                hash =hash >>>1;
            return hash;
        }finally {
            out.close();
            bout.close();
        }
    }
}

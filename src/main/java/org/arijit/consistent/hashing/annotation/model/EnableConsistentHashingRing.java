package org.arijit.consistent.hashing.annotation.model;

import org.arijit.consistent.hashing.algo.IHashAlgo;
import org.arijit.consistent.hashing.algo.Murmer3Hash;
import org.arijit.consistent.hashing.encoder.IEncoder;
import org.arijit.consistent.hashing.encoder.StringEncoder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableConsistentHashingRing {
    String basePackage();
    Class<?> keyEncoder() default StringEncoder.class;
    Class<?> hashAlgo() default Murmer3Hash.class;
}

package me.sunbird.javatest.concurrent;

import java.math.BigInteger;

/**
 * 作者：王豪
 * 日期：2021/12/5
 * 描述：<必填>
 */
class UnsafeCachingFactorizer {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHists;


    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHists / (double) hits;
    }


    private void service(Object req, Object resp) {
//        BigInteger i = extractFromRequest(req);
//        BigInteger[] factors = null;
//
//        synchronized (this) {
//            ++hits;
//            if (i.equals(lastNumber)) {
//                factors == lastFactors.clone();
//            }
//        }


    }

}

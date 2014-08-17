package org.test.soa.common.pool.tcp;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class PoolFactory {

    private static final int DEFAULT_EVICTABLE_TIME = 300000;
    private static final int DEFAULT_EVICTABLE_RUN_TIME = 30000;

    public static TcpConnectionPool createTcpPool(String host, int port, int timeout, int maxTotalIdle, int minTotalIdle, int maxTotal) throws Exception {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(maxTotalIdle);
        config.setMinIdle(minTotalIdle);
        config.setMaxTotal(maxTotal);
        config.setMinEvictableIdleTimeMillis(DEFAULT_EVICTABLE_TIME);
        config.setTimeBetweenEvictionRunsMillis(DEFAULT_EVICTABLE_RUN_TIME);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        config.setBlockWhenExhausted(true);
        TcpConnectionPool pool = new TcpConnectionPool(new TcpConnectionFactory(host, port, timeout), config);
        return pool;
    }

}

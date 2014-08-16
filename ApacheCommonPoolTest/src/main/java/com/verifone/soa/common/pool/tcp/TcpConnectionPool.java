package com.verifone.soa.common.pool.tcp;

import java.nio.channels.SocketChannel;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class TcpConnectionPool extends GenericObjectPool<SocketChannel> {

    public TcpConnectionPool(PooledObjectFactory<SocketChannel> factory, GenericObjectPoolConfig config) throws Exception {
        super(factory, config);
        for(int i=0; i<this.getMinIdle(); i++){
            this.addObject();
        }
    }
    
    
    

}

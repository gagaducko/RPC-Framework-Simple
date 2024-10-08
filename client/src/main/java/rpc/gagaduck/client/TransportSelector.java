package rpc.gagaduck.client;


import rpc.gagaduck.protocol.Peer;
import rpc.gagaduck.transport.TransportClient;

import java.util.List;

// decide which server to connect
public interface TransportSelector {

    void init(List<Peer> peers,
              int count,
              Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    void release(TransportClient client);

    void close();


}

package rpc.gagaduck.client;

import lombok.extern.slf4j.Slf4j;
import rpc.gagaduck.common.utils.ReflectionUtils;
import rpc.gagaduck.protocol.Peer;
import rpc.gagaduck.transport.TransportClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector {

    /**
     * 已经连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);

        for(Peer peer : peers){
            for(int i = 0; i < count; i++){
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
                log.info("connect server: {}", peer);
            }
        }

    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
//        return clients.remove(i);
        return clients.get(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);

    }

    @Override
    public synchronized void close() {
        for(TransportClient client : clients){
            client.close();
        }
        clients.clear();
    }
}

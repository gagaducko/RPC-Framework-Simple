package rpc.gagaduck.test;

import rpc.gagaduck.server.RpcServer;
import rpc.gagaduck.server.RpcServerConfig;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}

package rpc.gagaduck.client;

import rpc.gagaduck.codec.Decoder;
import rpc.gagaduck.codec.Encoder;
import rpc.gagaduck.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;


public class RpcClient {

    private final RpcClientConfig config;
    private final Encoder encoder;
    private final Decoder decoder;
    private final TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;

        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());

        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector)
        );
    }


}

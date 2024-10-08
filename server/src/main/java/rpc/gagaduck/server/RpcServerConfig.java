package rpc.gagaduck.server;

import lombok.Data;
import rpc.gagaduck.codec.Decoder;
import rpc.gagaduck.codec.Encoder;
import rpc.gagaduck.codec.JSONDecoder;
import rpc.gagaduck.codec.JSONEncoder;
import rpc.gagaduck.transport.HTTPTransportServer;
import rpc.gagaduck.transport.TransportServer;

/**
 * 服务端配置
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}

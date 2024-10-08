package rpc.gagaduck.client;

import lombok.Data;
import rpc.gagaduck.codec.Decoder;
import rpc.gagaduck.codec.Encoder;
import rpc.gagaduck.codec.JSONDecoder;
import rpc.gagaduck.codec.JSONEncoder;
import rpc.gagaduck.protocol.Peer;
import rpc.gagaduck.transport.HTTPTransportClient;
import rpc.gagaduck.transport.TransportClient;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;

    private List<Peer> servers= Arrays.asList(
            new Peer("127.0.0.1", 3000));
}

package rpc.gagaduck.server;

import rpc.gagaduck.common.utils.ReflectionUtils;
import rpc.gagaduck.protocol.Request;

/**
 * 调用具体服务
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParams());
    }
}

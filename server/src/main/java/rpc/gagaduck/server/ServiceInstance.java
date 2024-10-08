package rpc.gagaduck.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 服务的实例
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    private Object target;
    private Method method;
}

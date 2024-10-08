package rpc.gagaduck.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

// 表示服务
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    // 类名
    private String clazz;
    // 方法名
    private String method;
    // 返回的类型
    private String returnType;
    // 参数类型，多个参数，用数组
    private String[] paramTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());
        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for(int i=0;i<parameterClasses.length;i++){
            parameterTypes[i]=parameterClasses[i].getName();
        }
        sdp.setParamTypes(parameterTypes);
        return sdp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ServiceDescriptor)) return false;
        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + Arrays.toString(paramTypes) +
                '}';
    }
}

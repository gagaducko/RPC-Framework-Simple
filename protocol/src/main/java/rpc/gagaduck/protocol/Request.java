package rpc.gagaduck.protocol;

import lombok.Data;

// 表示请求
@Data
public class Request {
    // 首先是服务
    private ServiceDescriptor serviceDescriptor;
    // 参数
    private Object[] params;
}

package rpc.gagaduck.protocol;

import lombok.Data;

// 表示响应，RPC的返回
@Data
public class Response {
    // 通过一个code表示是否成功
    // 服务返回编码
    // 0-成功
    // 非0-失败
    private int code = 0;

    // 错误的具体原因
    private String message = "ok";

    // 返回的数据
    private Object data;
}

package rpc.gagaduck.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * HTTP传输服务端实现类，用于启动HTTP服务器并处理客户端请求。
 *
 * @Author : Gagaduck
 * @create 2024-10-8 21:41
 */
@Slf4j
public class HTTPTransportServer implements TransportServer{

    // 请求处理器，用于处理客户端发送的请求
    private RequestHandler handler;

    // Jetty服务器实例，用于启动和停止HTTP服务
    private Server server;

    /**
     * 初始化HTTP传输服务端，设置监听端口和请求处理器。
     *
     * @param port        服务器监听的端口号
     * @param handler     请求处理器实例，用于处理客户端请求
     */
    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");

    }

    /**
     * 启动HTTP服务器，使其开始监听并处理请求。
     */
    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    /**
     * 停止HTTP服务器，释放相关资源。
     */
    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    /**
     * 自定义的Servlet，用于处理客户端的POST请求。
     */
    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
            log.info("client connect");
            System.out.println("client connect");
            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();

            if (handler != null) {
                handler.onRequest(in, out);
            }

            out.flush();
        }
    }
}

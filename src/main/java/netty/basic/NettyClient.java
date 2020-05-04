package netty.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

// import org.hibernate.validator.constraints.NotBlank;
// import org.hibernate.validator.constraints.NotEmpty;


public class NettyClient {

    private String test;

    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建客户端的启动助手，完成相关配置
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group) // 设置线程组
                .channel(NioSocketChannel.class) // 设置客户端的实现类
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 往 pipeline 链中添加自定义的 handler
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        System.out.println("---------------- client is ready -----------------");
        // 启动客户端去连接服务器
        ChannelFuture cf = bootstrap.connect("localhost",9999).sync();
        // 关闭连接
        cf.channel().closeFuture().sync();
    }


}

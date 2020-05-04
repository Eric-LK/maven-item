package netty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyServer {
    // @SneakyThrows
    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程组：接受客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 创建一个线程组：处理网络操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 创建服务器端启动助手来配置参数
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup) // 设置两个线程组
                .channel(NioServerSocketChannel.class) // 使用 NioServerSocketChannel 作为服务器端通道的实现
                .option(ChannelOption.SO_BACKLOG,128) // 设置队列中等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE,true) // 保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道初始化对象
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 往 Pipeline 链中添加自定义的handler
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
        System.out.println("Server is ready------------");
        // 绑定端口，bind是异步的，但是sync是同步阻塞的
        ChannelFuture cf = b.bind(9999).sync();
        System.out.println("Server is starting ------------");
        // 关闭通道和线程组
        cf.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

    }
}

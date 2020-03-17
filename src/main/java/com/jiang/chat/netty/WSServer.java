package com.jiang.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jiang
 * \* Date: 2019/8/3
 * \* Time: 18:00
 * \* Description:创建一个单例 WSServer
 * \
 */
@Component
public class WSServer {

    Logger logger = Logger.getLogger(WSServer.class);

    private static class SingletionWSServer{
        static final WSServer instance = new WSServer();
    }

    // 获取一个单例对象
    public static WSServer getInstance(){
        return SingletionWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    // 构造函数初始化对象
    public WSServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    // 绑定端口
    public void start(){
        this.future = server.bind(8888);
        logger.debug("WSServer is running ... ");
    }


}
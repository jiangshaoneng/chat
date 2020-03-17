package com.jiang.chat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jiang
 * \* Date: 2019/8/3
 * \* Time: 18:25
 * \* Description:初始化器
 * \
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // websocket 基于http协议，所需要有http编码器
        pipeline.addLast(new HttpServerCodec());

        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

        // 对httpMessage进行聚合，聚合成 fullHttpRequest或FullHttpResponse
        // 几乎在netty中的编程，都会用到此hanler
        pipeline.addLast(new HttpObjectAggregator(1026*64));

        /**
         * websocket 服务器处理的协议，用于指定给客户端连接访问的路由:/ws
         * 本handler 会帮你处理一些繁琐的是
         * handshaking (close, ping, pong) ping + pong = 心跳
         * 对于 websocket来讲，都是以frames进行传输的，不同的数据 类型对应的frames也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 自定义的handler
        pipeline.addLast(new chatHandler());
    }
}
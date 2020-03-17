package com.jiang.chat.netty;

import com.jiang.chat.bean.ReceivedMessages;
import com.jiang.chat.bean.SendMessages;
import com.jiang.chat.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jiang
 * \* Date: 2019/8/3
 * \* Time: 18:38
 * \* Description:
 * \
 */
public class chatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取当前连接
        Channel currentChannel = ctx.channel();

        // 获取客户端传来的消息
        String content = msg.text();

        // 转成SendMessages
        SendMessages sendMessages = JsonUtils.jsonToPojo(content, SendMessages.class);

        // 根据不同的消息类型，分别处理
        if("connection".equals(sendMessages.getType())){
            // 保存当前的连接信息
            UserChannelRel.put(sendMessages.getNickName(), currentChannel);
            // 组装用户接收的消息
            ReceivedMessages receivedMessages = new ReceivedMessages();
            receivedMessages.setRoomName(sendMessages.getRoomName());
            receivedMessages.setNickName("*");
            receivedMessages.setType("info");
            receivedMessages.setMsg("["+ sendMessages.getNickName() + "]" + "加入聊天室");
            // 回复给客户端
            clients.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(receivedMessages)));

        }else if("normal".equals(sendMessages.getType())){
            ReceivedMessages receivedMessages = new ReceivedMessages();
            receivedMessages.setRoomName(sendMessages.getRoomName());
            receivedMessages.setNickName(sendMessages.getNickName());
            receivedMessages.setType("other");
            receivedMessages.setMsg(sendMessages.getMsg());
            clients.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(receivedMessages)));
        }
    }

    /**
     * 客户端连接服务端时, 获取客户端的channel, 并且保存到 ChannelGroup中
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        clients.remove(ctx.channel());
    }


}
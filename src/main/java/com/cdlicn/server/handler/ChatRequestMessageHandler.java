package com.cdlicn.server.handler;

import com.cdlicn.message.ChatRequestMessage;
import com.cdlicn.message.ChatResponseMessage;
import com.cdlicn.message.LoginRequestMessage;
import com.cdlicn.message.LoginResponseMessage;
import com.cdlicn.server.service.UserServiceFactory;
import com.cdlicn.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author cdlicn
 * @date 2026年01月26日 14:56
 * @description
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String to = msg.getTo();
        Channel channel = SessionFactory.getSession().getChannel(to);
        // 在线
        if (channel != null) {
            channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        }
        // 不在线
        else {
            ctx.channel().writeAndFlush(new ChatResponseMessage(false, "对方用户不存在或者不在线"));
        }
    }
}

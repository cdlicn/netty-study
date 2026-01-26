package com.cdlicn.server.handler;

import com.cdlicn.message.GroupCreateRequestMessage;
import com.cdlicn.message.GroupCreateResponseMessage;
import com.cdlicn.server.session.Group;
import com.cdlicn.server.session.GroupSession;
import com.cdlicn.server.session.GroupSessionFactory;
import com.cdlicn.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.Set;

/**
 * @author cdlicn
 * @date 2026年01月26日 15:09
 * @description
 */
@ChannelHandler.Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();
        // 群管理器
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.createGroup(groupName, members);
        if (group == null) {
            // 发送成功消息
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, groupName + "群创建成功"));
            // 发送拉群消息
            List<Channel> membersChannel = groupSession.getMembersChannel(groupName);
            for (Channel channel : membersChannel) {
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已被拉入"+groupName));
            }
        } else {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已经存在"));
        }

    }
}

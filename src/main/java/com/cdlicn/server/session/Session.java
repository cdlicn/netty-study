package com.cdlicn.server.session;

import io.netty.channel.Channel;

/**
 * 会话管理接口
 */
public interface Session {

    /**
     * 绑定 会话
     *
     * @param channel  {@link Channel} 哪个 channel 要绑定回话
     * @param username {@link String} 会话绑定用户
     */
    void bind(Channel channel, String username);

    /**
     * 解绑会话
     *
     * @param channel {@link Channel} 哪个 channel 要解绑会话
     */
    void unbind(Channel channel);

    /**
     * 获取会话属性
     *
     * @param channel {@link Channel}
     * @param name    {@link String}
     * @return {@link Object}
     */
    Object getAttribute(Channel channel, String name);

    /**
     * 设置会话属性
     *
     * @param channel {@link Channel}
     * @param name    {@link String}
     * @param value   {@link Object}
     */
    void setAttribute(Channel channel, String name, Object value);

    /**
     * 获取会话
     *
     * @param username {@link String} 用户名
     * @return {@link Channel}
     */
    Channel getChannel(String username);
}

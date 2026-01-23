package com.cdlicn.advance.c1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TestLengthFieldDecoder {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                // 创建解码器
                // maxFrameLength 最大帧长度
                // lengthFieldOffset 长度字段的偏移量
                // lengthFieldLength 长度字段的长度
                // lengthAdjustment 长度字段的调整，额外字节数
                // initialBytesToStrip 初始的字节跳过
                new LengthFieldBasedFrameDecoder(
                        1024, 0, 4, 1, 4),
                new LoggingHandler(LogLevel.DEBUG)
        );

        // 4 个字节的内容长度，实际内容
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        send(buffer, "Hello, world");
        send(buffer, "Hi!");
        channel.writeInbound(buffer);
    }

    private static void send(ByteBuf buffer, String content) {
        byte[] bytes = content.getBytes(); // 实际内容
        int length = bytes.length; // 实际内容长度
        buffer.writeInt(length);
        buffer.writeByte(1);
        buffer.writeBytes(bytes);
    }
}

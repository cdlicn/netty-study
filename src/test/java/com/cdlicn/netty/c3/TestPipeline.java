package com.cdlicn.netty.c3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;


@Slf4j
public class TestPipeline {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        // 1. 通过 channel 拿到 pipeline
                        ChannelPipeline pipeline = channel.pipeline();
                        // 2. 添加处理器 head -> h1 -> h2 -> h3 -> h4 -> h5 -> h6 -> tail
                        // 入站 从前往后 1 2 3
                        pipeline.addLast("h1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("1");
//                                ByteBuf buf = (ByteBuf) msg;
//                                String name = buf.toString(Charset.defaultCharset());
//                                super.channelRead(ctx, name); // 将数据传递给下一个handler，如果不调用，调用链会断开
//                                ctx.fireChannelRead(name);
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("2");
//                                Student student = new Student(name.toString());
//                                super.channelRead(ctx, student);
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h3", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                log.debug("3，结果{}，class:{}", msg, msg.getClass());
                                log.debug("3");
                                // 从尾往前执行
//                                channel.writeAndFlush(ctx.alloc().buffer().writeBytes("server...".getBytes()));
                                // 从当前位置往前执行
                                ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("hello...".getBytes()));
                            }
                        });
                        // 出站 从后往前 6 5 4，执行 writeAndFlush 才会执行
                        pipeline.addLast("h4", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("4");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("h5", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("5");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("h6", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("6");
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                })
                .bind(8080);
    }

    @Data
    @AllArgsConstructor
    static class Student {
        private String name;
    }

}

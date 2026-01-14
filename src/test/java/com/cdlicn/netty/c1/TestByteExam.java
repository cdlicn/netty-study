package com.cdlicn.netty.c1;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import static com.cdlicn.netty.c1.ByteBufferUtil.debugAll;

/**
 * @author cdlicn
 * @date 2026年01月14日 23:04
 * @description
 */

public class TestByteExam {
    public static void main(String[] args) {
        /*
        网络上有多条数据发送给服务器，数据之间用 \n 进行分隔
        但由于某种原因这些数据在接收时，倍进行了重新组合，例如原始数据有3条为
        Hello,world\n
        I'm zhangsan\n
        How are you?\n
        变成了下面的两个byteBuffer（黏包，半包）
        Hello,world\nI'm zhangsan\nHo
        w are you?\n
        现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整消息
            if (source.get(i) == '\n') {
                int len = i + 1 - source.position();
                // 把这条完整消息存入新的ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(len);
                // 从 source 读，向 target 写
                for (int j = 0; j < len; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }

}

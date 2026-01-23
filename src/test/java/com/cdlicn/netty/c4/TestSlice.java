package com.cdlicn.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.cdlicn.netty.c4.TestByteBuf.log;

public class TestSlice {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'});
        log(buf);

        // 在切片的过程中，没有发生数据的复制，禁止扩容
        ByteBuf f1 = buf.slice(0, 5);
        f1.retain(); // 计数器加一，防止禁用 buf 后，影响 f1
        ByteBuf f2 = buf.slice(5, 5);
        f2.retain();
        log(f1);
        log(f2);

        /*System.out.println("==============================================================================");
        f1.setByte(0, 'b');
        log(f1);
        log(buf);*/

        // 释放原有 ByteBuf 内存后，直接使用 f1 会报错
        System.out.println("释放原有 ByteBuf 内存");
        buf.release();
        log(f1);
        f1.release(); // 释放 f1
        f2.release(); // 释放 f2

    }
}

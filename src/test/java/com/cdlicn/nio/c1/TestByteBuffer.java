package com.cdlicn.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author cdlicn
 * @date 2026年01月13日 22:49
 * @description
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel
        // 1. 输入输出流 2. RandomAccessFile
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                // 从channel读取数据，向 buffer 写入，读出长度为len，len为-1表示读取结束
                int len = channel.read(buffer);
                log.debug("读取到的字节数：{}", len);
                if (len == -1) {
                    break;
                }
                // 打印 buffer 的内容
                buffer.flip(); // 切换到读模式
                while (buffer.hasRemaining()) { // 是否还有剩余未读数据
                    byte b = buffer.get(); // 读一个字节
                    log.debug("实际字节：{}", (char) b);
                }
                // 切换为写模式
                buffer.clear();
            }
        } catch (IOException e) {
        }
    }
}

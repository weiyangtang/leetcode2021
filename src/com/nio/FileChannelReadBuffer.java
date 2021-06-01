package com.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tangweiyang
 * @Description:
 * @date 2021/5/21 上午10:30
 */
public class FileChannelReadBuffer {
    public static void main(String[] args) throws IOException {
        File file = new File("demo.txt");
        System.out.println(file.exists());
        RandomAccessFile randomAccessFile = new RandomAccessFile("demo.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            System.out.println("read:" + byteBuffer);
            // buffer 翻转
            byteBuffer.flip();

            // 是否有剩余元素
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }
}

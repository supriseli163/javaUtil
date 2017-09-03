package com.base.data.access.alyer.netty;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;

import java.util.List;
public class ClientWriteBuf {
    private final List<ByteBuf> bufs = Lists.newArrayList();

    private int bufSize;

    public ClientWriteBuf() {

    }

    public int write(ByteBuf byteBuf) {
        bufs.add(byteBuf);
        bufSize += byteBuf.readableBytes();
        return bufSize;
    }

    public int getBufSize() {
        return bufSize;
    }

    public ByteBuf readall() {
        try {
            if (this.bufs.isEmpty()) {
                return Unpooled.EMPTY_BUFFER;
            }
            if (this.bufs.size() == 1) {
                return bufs.get(0);
            }
            return new CompositeByteBuf(UnpooledByteBufAllocator.DEFAULT, false, bufs.size(), bufs);
        } finally {
            bufSize=0;
            bufs.clear();
        }
    }
}

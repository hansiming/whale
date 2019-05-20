package com.cszjo.whale.core.server;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class WhaleDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> out) throws Exception {
        in.markReaderIndex();
        short magicNum = in.readShort();
        in.skipBytes(1);
        byte messageType = in.readByte();

        long requestId = in.readLong();
        int dataSize = in.readInt();
        byte[] data = new byte[dataSize];
        in.readBytes(data);

        WhaleMessage message = new WhaleMessage.Builder()
                .requestId(requestId)
                .isRequest(messageType == (0x00))
                .data(data)
                .build();

        out.add(message);
    }
}

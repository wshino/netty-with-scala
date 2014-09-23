package io.netty.example.time

import java.util

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ReplayingDecoder

class TimeDecoder extends ReplayingDecoder[Unit] {

  override def decode(ctx: ChannelHandlerContext, in: ByteBuf, out: util.List[Object]): Unit = {
    out.add(UnixTime(in.readInt()))
  }
}

package io.netty.example.time

import java.util

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.{ReplayingDecoder, ByteToMessageDecoder}

class TimeDecoder extends ReplayingDecoder[Unit] {
  override def decode(ctx: ChannelHandlerContext, in: ByteBuf, out: util.List[AnyRef]): Unit = {
     out.add(in.readBytes(4))
  }
}

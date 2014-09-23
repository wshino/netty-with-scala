package io.netty.example.time

import java.util.Date

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, ChannelHandlerAdapter}

class TimeClientHandler extends ChannelHandlerAdapter{

  override def channelRead(ctx: ChannelHandlerContext, msg: Object) = {
    val m = msg.asInstanceOf[ByteBuf]
    try {
      val currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L
      println(new Date(currentTimeMillis))
      ctx.close()
    } finally {
      m.release()
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
    cause.printStackTrace()
    ctx.close()
  }

}

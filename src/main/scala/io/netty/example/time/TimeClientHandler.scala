package io.netty.example.time

import java.util.Date

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, ChannelHandlerAdapter}

class TimeClientHandler extends ChannelHandlerAdapter{

  private var buf: ByteBuf = null

  override def handlerAdded(ctx: ChannelHandlerContext): Unit = {
    buf = ctx.alloc().buffer(4)
  }

  override def handlerRemoved(ctx: ChannelHandlerContext) = {
    buf.release()
    buf = null
  }

  override def channelRead(ctx: ChannelHandlerContext, msg: Object) = {
    val m = msg.asInstanceOf[ByteBuf]
    buf.writeBytes(m)
    m.release()

    if (buf.readableBytes() >= 4) {
      val currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L
      println(new Date(currentTimeMillis))
      ctx.close()
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
    cause.printStackTrace()
    ctx.close()
  }

}

package io.netty.example.time

import io.netty.channel.{ChannelFutureListener, ChannelHandlerAdapter, ChannelHandlerContext}

class TimeServerHandler extends ChannelHandlerAdapter {

  override def channelActive(ctx: ChannelHandlerContext) = {
    val a = UnixTime.apply()
    val f = ctx.writeAndFlush(a)
    f.addListener(ChannelFutureListener.CLOSE)
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) = {
    cause.printStackTrace()
    ctx.close()
  }
}

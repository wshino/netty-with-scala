package io.netty.example.time

import io.netty.channel.{ChannelHandlerAdapter, ChannelHandlerContext}

class TimeClientHandler extends ChannelHandlerAdapter {

  override def channelRead(ctx: ChannelHandlerContext, msg: Object) = {
    val m = msg.asInstanceOf[UnixTime]
    println("server time " + m )
    ctx.close()
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
    cause.printStackTrace()
    ctx.close()
  }

}

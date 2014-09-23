package io.netty.example.discard

import io.netty.channel.{ChannelFutureListener, ChannelHandlerAdapter, ChannelHandlerContext}
import io.netty.example.time.UnixTime

class DiscardServerHandler extends ChannelHandlerAdapter {

  override def channelRead(ctx: ChannelHandlerContext, msg: Object) = {
    ctx.write(msg)
    ctx.flush()
  }


  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) = {
    cause.printStackTrace()
    ctx.close()
  }
}

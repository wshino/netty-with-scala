package io.netty.example.time

import io.netty.channel.{ChannelFuture, ChannelFutureListener, ChannelHandlerAdapter, ChannelHandlerContext}

class TimeServerHandler extends ChannelHandlerAdapter {

  override def channelActive(ctx: ChannelHandlerContext) = {
    val time = ctx.alloc().buffer(4)
    time.writeInt((System.currentTimeMillis() / 1000L + 2208988800L).asInstanceOf[Int])

    val f = ctx.writeAndFlush(time)
    f.addListener(new ChannelFutureListener {
      override def operationComplete(future: ChannelFuture) = {
        assert(f == future)
        ctx.close()
      }
    })
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) = {
    cause.printStackTrace()
    ctx.close()
  }
}

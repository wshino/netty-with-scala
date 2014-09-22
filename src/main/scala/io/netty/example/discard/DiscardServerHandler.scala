package io.netty.example.discard

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, ChannelHandlerAdapter}
import io.netty.util.ReferenceCountUtil

/**
 * Created by JP16163 on 2014/09/22.
 */
class DiscardServerHandler extends ChannelHandlerAdapter{

  override def channelRead(ctx: ChannelHandlerContext, msg: Object) = {
    var in = msg.asInstanceOf[ByteBuf]
    try {
      while (in.isReadable()) {
        System.out.println(in.readByte())
        System.out.flush()
      }
    } finally {
      ReferenceCountUtil.release(msg)
    }
//    (msg.asInstanceOf[ByteBuf]).release()
  }


  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) = {
    cause.printStackTrace()
    ctx.close()
  }
}

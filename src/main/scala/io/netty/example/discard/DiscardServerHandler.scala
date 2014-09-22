package io.netty.example.discard

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, ChannelHandlerAdapter}
import io.netty.util.ReferenceCountUtil

class DiscardServerHandler extends ChannelHandlerAdapter{

//  override def channelRead(ctx: ChannelHandlerContext, msg: Object) = {
//    val in = msg.asInstanceOf[ByteBuf]
//    try {
//      while (in.isReadable) {
//        System.out.println(in.readByte())
//        System.out.flush()
//      }
//    } finally {
//      ReferenceCountUtil.release(msg)
//    }
//  }
    // echo server
    override def channelRead(ctx: ChannelHandlerContext, msg: Object) = {
      ctx.write(msg)
      ctx.flush()
    }


  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) = {
    cause.printStackTrace()
    ctx.close()
  }
}

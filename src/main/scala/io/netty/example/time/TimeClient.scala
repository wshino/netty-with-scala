package io.netty.example.time



import io.netty.bootstrap.Bootstrap
import io.netty.channel.socket.SocketChannel
import io.netty.channel.{ChannelInitializer, ChannelOption}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

object TimeClient extends App {
  val host = args(0)
  val port: Int = Integer.parseInt(args(1))
  val workerGroup = new NioEventLoopGroup()

  try {
    val b: Bootstrap = new Bootstrap
    b.group(workerGroup)
    b.channel(classOf[NioSocketChannel])
    b.option[java.lang.Boolean](ChannelOption.SO_KEEPALIVE, true)
    b.handler(new ChannelInitializer[SocketChannel] {
      override def initChannel(ch: SocketChannel): Unit = {
        ch.pipeline().addLast(new TimeDecoder, new TimeClientHandler)
      }
    })

    val f = b.connect(host, port).sync()

    f.channel().closeFuture().sync()
  } finally {
    workerGroup.shutdownGracefully()
  }
}
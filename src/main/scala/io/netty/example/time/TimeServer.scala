package io.netty.example.time

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.{ChannelOption, ChannelInitializer}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel

// 37 番がかぶってるから動作確認してない
class TimeServer(p:Int) {

  private val port: Int = p

  def run() = {
    val bossGroup = new NioEventLoopGroup()
    val workerGroup = new NioEventLoopGroup()

    try {
      val b = new ServerBootstrap()
      .group(bossGroup, workerGroup)
      .channel(classOf[NioServerSocketChannel])
      .childHandler(new ChannelInitializer[SocketChannel] {
        override def initChannel(ch: SocketChannel) = {
          ch.pipeline().addLast(new TimeServerHandler)
        }
      })
      .option[Integer](ChannelOption.SO_BACKLOG, 128)
      .childOption[java.lang.Boolean](ChannelOption.SO_KEEPALIVE, true)

      val f = b.bind(port).sync()
      f.channel().closeFuture().sync()

    } finally {
      workerGroup.shutdownGracefully()
      bossGroup.shutdownGracefully()
    }
  }
}

object TimeServer extends App {

  val port = args.length match {
    case x if x > 0 => Integer.parseInt(args(0))
    case _ => 8080
  }
  new TimeServer(port).run()
}
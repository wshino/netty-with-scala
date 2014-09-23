package io.netty.example.time

case class UnixTime(value: Long = (System.currentTimeMillis() / 1000L + 2208988800L))
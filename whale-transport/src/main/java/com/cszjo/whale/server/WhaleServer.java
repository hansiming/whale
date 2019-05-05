package com.cszjo.whale.server;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicBoolean;

import com.cszjo.whale.common.exception.ExceptionCode;
import com.cszjo.whale.common.exception.WhaleException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

public class WhaleServer {

    private static final AtomicBoolean isStarted = new AtomicBoolean();

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private InetAddress inetAddress;
    private int port;
    private Channel channel;

    public WhaleServer(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

    public void start() {
        if (isStarted.compareAndSet(false, true)) {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            open();
        }

        throw new WhaleException(ExceptionCode.SERVER_ALREADY_STARTED);
    }

    public void close() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }

    private void open() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workerGroup)
                .childHandler(new WhaleServerChannelInitializer())
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .bind(this.inetAddress, this.port);
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
    }

    private static class WhaleServerChannelInitializer
            extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {

        }
    }
}

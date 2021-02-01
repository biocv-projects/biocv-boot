package com.biocv.boot.protocol;

import com.biocv.boot.protocol.handler.JsonHandler;
import com.biocv.boot.protocol.provider.ProviderManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.Provider;

/**
 * server
 *
 * @author Tyler.Feng
 * @date 2021-01-15 19:07
 * @since 1.0.0
 */
@Component
public class NettyServer implements CommandLineRunner {

    @Autowired
    private ProviderManager providerManager;

    @Override
    public void run(String... args) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new GlassesInitializer(providerManager));

            // Start the server.
            ChannelFuture channelFuture = serverBootstrap.bind(9528).sync();

            // Wait until the server socket is closed.
            channelFuture.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

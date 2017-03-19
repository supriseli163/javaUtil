package com.base.java.util.netty.tcp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChInitailzer extends ChannelInitializer<Channel> {
    private final IChInitializer initializer;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        initializer.initChannel(ch);
    }
}

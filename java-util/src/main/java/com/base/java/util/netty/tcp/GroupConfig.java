package com.base.java.util.netty.tcp;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class GroupConfig {
    private int threads;
    private ChannelHandler channelHandler;
    private Map<ChannelOption<?>, Object> options = new HashMap<>();

    public GroupConfig setThreads(int threads) {
        if (threads < 0) {
            throw new IllegalStateException("Threads cannot be negative:" + threads + "!");
        }

        this.threads = threads;
        return this;
    }

    public GroupConfig setChannelHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        return this;
    }

    public <T> GroupConfig setOption(ChannelOption<T> option, T value) {
        options.put(option, value);
        return this;
    }
}

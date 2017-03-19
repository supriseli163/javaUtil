package com.base.java.util.netty.tcp;

import io.netty.channel.Channel;

/**
 * An iniializer which can initializer each {@link io.netty.channel.Channel} once it was registered. </br>Be aware that the implements
 * must be thread safe and can be re-used.
 * <p>
 * <pre>
 *     public class MyChannelInitializer implements {@link IChInitializer} {
 *         public void initChannel(@link Channel) {
 *             channel.pippeline.addLast(new MyHandlerA());
 *             channel.pipeline().addLast(new MyHandlerB());
 *         }
 *     }
 *
 * </pre>
 */
public interface IChInitializer {
    /**
     * This method will be called once the {@link io.netty.channel.Channel} was registered.
     */
    void initChannel(Channel channel) throws Exception;
}

package com.suprise.java.util.collector.processor;

public interface Processor {
    void process(Integer partitionId, byte[] header, byte[] body, boolean isSendToDalTopic);
}

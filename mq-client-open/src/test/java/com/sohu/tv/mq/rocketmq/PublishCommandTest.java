package com.sohu.tv.mq.rocketmq;

import java.util.HashMap;
import java.util.Map;

import org.apache.rocketmq.client.producer.SendResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sohu.index.tv.mq.common.Result;

public class PublishCommandTest {
    private RocketMQProducer producer;

    @Before
    public void init() {
        producer = TestUtil.buildProducer("core-vrs-topic-producer", "core-vrs-topic");
        producer.start();
    }

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 1000; ++i) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("aid", "123456" + i);
            map.put("vid", "123457" + i);
            Result<SendResult> result = new PublishCommand(producer, map).execute();
            if (!result.isSuccess()) {
                System.out.println("err");
            }
            Thread.sleep(500);
        }
    }

    @After
    public void clean() {
        producer.shutdown();
    }
}

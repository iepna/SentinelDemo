package com.ifly.vvm.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer(Constants.TEST_GROUP_NAME);
        producer.setNamesrvAddr("10.2.38.8:9876");

        // Launch the instance.
        producer.start();
        for (int i = 0; i < 1000; i++) {
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message(Constants.TEST_TOPIC_NAME, "TagA",
                    ("Hello RocketMQ From Sentinel " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            //System.out.printf("%s%n", sendResult);
        }
        // Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}

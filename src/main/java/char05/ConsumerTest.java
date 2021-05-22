package char05;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.util.*;


/**
 * @author zhuyufeng
 * @version 1.0
 * @date 2021-04-13
 * @Description:
 */
public class ConsumerTest {

    /*
Topic: partitionTest	PartitionCount: 4	ReplicationFactor: 1	Configs:
	Topic: partitionTest	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: partitionTest	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
	Topic: partitionTest	Partition: 2	Leader: 0	Replicas: 0	Isr: 0
	Topic: partitionTest	Partition: 3	Leader: 0	Replicas: 0	Isr: 0
Topic: serializerTest	PartitionCount: 1	ReplicationFactor: 1	Configs:
	Topic: serializerTest	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
Topic: test	PartitionCount: 1	ReplicationFactor: 1	Configs:
	Topic: test	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
Topic: test2	PartitionCount: 4	ReplicationFactor: 1	Configs:
	Topic: test2	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: test2	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
	Topic: test2	Partition: 2	Leader: 0	Replicas: 0	Isr: 0
	Topic: test2	Partition: 3	Leader: 0	Replicas: 0	Isr: 0
Topic: yfzhuTest	PartitionCount: 1	ReplicationFactor: 1	Configs:
	Topic: yfzhuTest	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
     */
    public static void main(String[] args) {
        String topicName = "partitionTest";
        String groupId = "test-group";

        Properties properties= new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.103.7.129:9092");//必须指定
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);//必须指定
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<Integer, String> consumer= new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topicName));

        List<TopicPartition> partitions = new ArrayList<>();
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("partitionTest");

        for (PartitionInfo partitionInfo : partitionInfos) {
            partitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
        }

        consumer.assign(partitions);
    }
}

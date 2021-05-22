package char04;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.RetriableException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhuyufeng
 * @version 1.0
 * @date 2021-03-30
 * @Description:
 */
public class ProducerTest {

    public static void main(String[] args) {
        Properties properties= new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.103.7.129:9092");//必须指定
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");//必须指定
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");//必须指定
        //properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "char04.SerializerTest");//必须指定
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1000);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1048576);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 3000);
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10485760);
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "char04.PartitionTest");

        List<String> interceptors = new ArrayList<>();
        interceptors.add("char04.InterceptorTest");
        interceptors.add("char04.CounterInterceptor");

        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        //Producer<Integer, String> producer= new KafkaProducer<>(properties);
        Producer<Integer, String> producer= new KafkaProducer<>(properties);

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " 开始发送");

        for (int i = 0;i < 10;i++) {
            producer.send(new ProducerRecord<>("partitionTest", 100, "hello consumer"),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if (exception == null) {
                                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " 消息发送成功");
                            } else {
                                if (exception instanceof RetriableException) {
                                    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " 处理可重试异常");
                                    System.out.println(exception.getClass());
                                } else {
                                    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + "处理不可重试异常");
                                }
                            }
                        }
                    });
        }

        producer.close();
    }
}

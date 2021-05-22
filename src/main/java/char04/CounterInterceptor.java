package char04;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author zhuyufeng
 * @version 1.0
 * @date 2021-04-08
 * @Description:
 */
public class CounterInterceptor implements ProducerInterceptor<Integer, User> {

    private int errorCounter = 0;
    private int successCounter = 0;

    @Override
    public ProducerRecord<Integer, User> onSend(ProducerRecord<Integer, User> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            successCounter++;
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " 成功数量：" + successCounter);
        } else {
            errorCounter++;
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " 失败数量：" + errorCounter);
        }
    }

    @Override
    public void close() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " CounterInterceptor关闭");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

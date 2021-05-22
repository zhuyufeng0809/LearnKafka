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
public class InterceptorTest implements ProducerInterceptor<Integer, User> {

    @Override
    public ProducerRecord<Integer, User> onSend(ProducerRecord<Integer, User> record) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " 执行过滤操作");
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " InterceptorTest关闭");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

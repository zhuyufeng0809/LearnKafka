package char04;

import org.apache.kafka.common.serialization.Serializer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author zhuyufeng
 * @version 1.0
 * @date 2021-04-07
 * @Description:
 */
public class SerializerTest implements Serializer<User> {

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, User user) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " 开始序列化");

        Kryo kryo = new Kryo();
        kryo.register(User.class);

        Output output = new Output(500);
        kryo.writeObject(output, user);

        byte[] bytes = output.getBuffer();
        output.close();
        return bytes;
    }

    @Override
    public void close() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) + Thread.currentThread().getName() + " SerializerTest关闭");
    }
}
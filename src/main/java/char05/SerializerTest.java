package char05;

import char04.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author zhuyufeng
 * @version 1.0
 * @date 2021-05-12
 * @Description:
 */
public class SerializerTest implements Deserializer<User> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public User deserialize(String topic, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}

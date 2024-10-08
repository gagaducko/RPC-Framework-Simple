package rpc.gagaduck.codec;

import org.junit.Test;
import static org.junit.Assert.*;

public class JSONEncoderTest {
    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("gagaduck");
        bean.setAge(22);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}

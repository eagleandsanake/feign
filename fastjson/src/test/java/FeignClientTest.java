import feign.RequestTemplate;
import feign.fastjson.FastjsonEncoder;
import org.junit.jupiter.api.Test;
import static feign.assertj.FeignAssertions.assertThat;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wuxin
 * @date 2024/04/04 12:31:18
 */
public class FeignClientTest {


    @Test
    void encodesMapObjectNumericalValuesAsInteger() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("foo", 1);

        RequestTemplate template = new RequestTemplate();
        new FastjsonEncoder().encode(map, map.getClass(), template);

        assertThat(template).hasBody("" //
                + "{\n" //
                + "  \"foo\": 1\n" //
                + "}");
    }



}

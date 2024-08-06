package feign.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import static feign.Util.UTF_8;
import static feign.Util.ensureClosed;

/**
 * @author wuxin
 * @date 2024/04/03 00:34:06
 */
public class FastJsonDecoder implements Decoder {


    @Overridefei
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.status() == 404 || response.status() == 204)
            return Util.emptyValueOf(type);
        if (response.body() == null)
            return null;
        Reader reader = response.body().asReader(UTF_8);
        try {
            return JSON.parseObject(Util.toString(reader),type);
        } catch (JSONException e) {
            if (e.getCause() != null && e.getCause() instanceof IOException) {
                throw IOException.class.cast(e.getCause());
            }
            throw e;
        } finally {
            ensureClosed(reader);
        }
    }
}

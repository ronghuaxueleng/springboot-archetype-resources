#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * JSON工具类 （jackson ）
 *
 * @Author: caoqiang
 * @Date: 2023/8/16 0016 下午 15:15
 */
public class JSONUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

  public static final ObjectMapper mapper = new ObjectMapper();

  static {
    // 转换为格式化的json
    //mapper.enable(SerializationFeature.INDENT_OUTPUT);
    // 如果json中有新增的字段并且是实体类类中不存在的，不报错
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //修改日期格式
    //mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
  }


  /**
   * 对象转字符串
   *
   * @param object
   * @return
   */
  public static String toJSONString(Object object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      LOGGER.error(">> convert to json failed ", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 字符串转对象
   *
   * @param json
   * @return
   */
  public static <T> T parseObject(String json, Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    } catch (Exception e) {
      LOGGER.error(">> parseObject failed ", e);
      throw new RuntimeException(e);
    }
  }


  /**
   * 字符串转ObjectNode (ObjectNode类似 fastjson 的 JSONObject)
   * <p>
   * 使用参考 https://blog.csdn.net/sinat_40770656/article/details/106207324#1Datelong_499
   *
   * @param json
   * @return
   */
  public static ObjectNode parseObject(String json) {
    try {

      return (ObjectNode) mapper.readTree(json);
    } catch (Exception e) {
      LOGGER.error(">> parseObject to ObjectNode  failed ", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 字符串转对象列表
   *
   * @param json
   * @param clazz
   * @param <T>
   * @return
   * @throws IOException
   */
  public static <T> List<T> parseArray(String json, Class<T> clazz) {
    try {
      // 注意这里之前有个bug
      // return  mapper.readValue(json,new TypeReference<List<T>>(){});
      JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
      return mapper.readValue(json, javaType);
    } catch (Exception e) {
      LOGGER.error(">> parseArray failed ", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 字符串转ArrayNode ,  (ArrayNode类似 fastjson 的 JSONArray)
   *
   * @param json
   * @return
   */
  public static ArrayNode parseArray(String json) {
    try {
      return (ArrayNode) mapper.readTree(json);
    } catch (Exception e) {
      LOGGER.error(">> parseArray to ArrayNode  failed ", e);
      throw new RuntimeException(e);
    }
  }

}

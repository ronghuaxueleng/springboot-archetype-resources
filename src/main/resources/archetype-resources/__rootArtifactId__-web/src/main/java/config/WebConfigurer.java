#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 在web的配置文件中，实例化登陆的拦截器，并添加规则
 *
 * @author caoqiang
 * @date 2020/1/7 0007下午 15:19
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  /**
   * 添加静态资源文件，外部可以直接访问地址
   * 这个配置在前后端分离的情况下没啥用，因为没有静态资源
   *
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
  }

}

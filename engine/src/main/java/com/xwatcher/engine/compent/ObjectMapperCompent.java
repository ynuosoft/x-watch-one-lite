package com.xwatcher.engine.compent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by meng li on 2017/3/9.
 */
@Configuration
public class ObjectMapperCompent {

    /**
     * @return
     */
    @Scope("singleton")
    @Bean
    public  ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
//         配置mapper忽略空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//        当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
//        因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        容许json中有注释
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//         为了使JSON视觉上的可读性，增加一行如下代码，注意，在生产中不需要这样，因为这样会增大Json的内容
//        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //设置日期格式当使用jackson在处理时间时，默认是将时间输出为timestamps格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZ"));

        objectMapper.registerModule(new JodaModule());

        return objectMapper;
    }

}

package com.zt.web.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactoryBuilder;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author zt
 * @since 2023/8/29 09:49
 **/
public class YamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper objectMapper;

    public YamlHttpMessageConverter() {
        // 告诉 Springboot 这个 MessageConverter 支持哪种媒体类型
        super(new MediaType("text", "yaml", StandardCharsets.UTF_8));
        YAMLFactoryBuilder factoryBuilder = YAMLFactory.builder().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(factoryBuilder.build());
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 如果是对象类型可以转换为yaml
        if (clazz.getTypeName().equals("Object")) {
            System.out.println("#########");
        }
        return true;
    }

    @Override // @RequestBody 接收请求对象参数
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override // @ResponseBody 把对象写出去
    protected void writeInternal(Object methodReturnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        objectMapper.writeValue(outputMessage.getBody(), methodReturnValue);
    }
}

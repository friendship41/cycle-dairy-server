package com.friendship41.cycledairyserver.data.type.converter;

import com.friendship41.cycledairyserver.data.type.ReturnType;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ReturnTypeConverter implements Converter<String, ReturnType> {
  @Override
  public ReturnType convert(final String s) {
    return ReturnType.valueOf(s.toUpperCase());
  }
}

package org.domain.restfulapiarticle.test.config;

import java.nio.charset.Charset;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;

@Configuration
@Profile("test")
public class ControllerTestConfig {
	 public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
             MediaType.APPLICATION_JSON.getSubtype(),                        
             Charset.forName("utf8")                     
             );
}

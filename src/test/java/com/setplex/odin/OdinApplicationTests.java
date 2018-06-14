package com.setplex.odin;

import com.setplex.odin.config.HttpClientConfig;
import com.setplex.odin.config.RestTemplateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RestTemplateConfig.class, HttpClientConfig.class})
@SpringBootTest
public class OdinApplicationTests {




    @Test
    public void contextLoads() {
//        final String uri = "http://localhost:8080/nora/api/odin/provider";
//        ProviderStatus status = ProviderStatus.DISABLED;
//        String input = "{\"status\":\"ENABLED\"}";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(input, headers);
//
//        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("changeme", "changeme"));
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        restTemplate.postForObject(uri, entity, String.class);

       // System.out.println(applicationSettings.getProviderStatus());

        //System.out.println(String.format(applicationSettings.getProviderStatus(), status));
    }

}

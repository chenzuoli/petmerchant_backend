package pet.petcage;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import pet.petcage.common.Constant;

@SpringBootApplication
@EnableConfigurationProperties(Constant.class)
public class PetMerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetMerchantApplication.class, args);
    }

    // 支持http
    @Bean
    public Connector connector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8182);
        connector.setSecure(false);
        connector.setRedirectPort(7553);
        return connector;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector) {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);

        //解决请求中带[]{}等特殊字符的参数值
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) tomcat_connector -> tomcat_connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return tomcat;
    }

}

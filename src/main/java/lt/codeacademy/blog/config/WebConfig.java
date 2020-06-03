package lt.codeacademy.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
      LocaleChangeInterceptor localeChangeInterceptor= new LocaleChangeInterceptor();
      localeChangeInterceptor.setParamName("lang");
      return  localeChangeInterceptor;
  }
  @Bean
  public LocaleResolver localeResolver(){
     CookieLocaleResolver cookieLocaleResolver=new CookieLocaleResolver();
     cookieLocaleResolver.setDefaultLocale(new Locale("lt", "LT"));
     return  cookieLocaleResolver;
  }


  public void addInterceptors(InterceptorRegistry interceptorRegistry){
      interceptorRegistry.addInterceptor(localeChangeInterceptor());
  }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/signIn").setViewName("login");
    }
}

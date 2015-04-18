package codingsaint.configurations;


import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * <code>MVCConfiguration </code> : Lets app know that this is a web app through
 * Spring
 * 
 * @author Kumar Pallav
 * @since 1.0
 *
 */
@EnableWebMvc
@Configuration
public class MVCConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env;
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * <code>getInternalResourceViewResolver</code> : View needs to be sent so
	 * Lets resolve it It adds a prefix of WEB-INF/jsp and postfix of .jsp , so
	 * if Controller says index , we get WEB-INF/jsp/index.jsp
	 * 
	 * @return resolved view
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	/**
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver.classname"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
		
	}
	

	/**
	 * 
	 * @return PostProcesser Bean of LOGGER
	 */
	@Bean
	public static LoggerPostProcessor activateLoggerPostProcessor() {
		return new LoggerPostProcessor();
	}
	@Bean
	 public LocaleChangeInterceptor localeChangeInterceptor(){
	     LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
	     localeChangeInterceptor.setParamName("language");
	     return localeChangeInterceptor;
	 }
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}
	 
	 @Bean(name = "localeResolver")
	 public LocaleResolver sessionLocaleResolver(){
	     SessionLocaleResolver localeResolver=new SessionLocaleResolver();
	     localeResolver.setDefaultLocale(new Locale("en"));
	    
	      
	     return localeResolver;
	 } 
	  
	 public void addInterceptors(InterceptorRegistry registry) {
	     registry.addInterceptor(localeChangeInterceptor());
	 }
}

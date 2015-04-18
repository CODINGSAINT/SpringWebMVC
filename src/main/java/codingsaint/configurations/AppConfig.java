package codingsaint.configurations;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
/**
 * <code>AppConfig </code> : Base configuration file
 * @author Coding Saint
 *
 */
//@PropertySource("/WEB-INF/classes/environment/${folder}/application.properties")
@Configuration
//@EnableCaching
@ComponentScan(basePackages = { "codingsaint.*" })
@PropertySource("classpath:application.properties")

@Import({ MVCConfiguration.class , SecurityConfiguration.class})//

public class AppConfig  {
	@Autowired
	Environment env;

	/**
	 * <code>ClientHttpRequestFactory</code> :Loads caches. All cache members
	 * should be declared. its configured via property file [application.properties]
	 * 
	 * @return
	 
	@Bean
	public CacheManager cacheManager() {
		// configure and return an implementation of Spring's CacheManager SPI
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(getCacheMembers());
		return cacheManager;
	}
*/
	

	/**
	 * Adds all comma separated cache members to List and returns as List of
	 * ConcurrentMapCache
	 * 
	 * @return List<ConcurrentMapCache>
	 */
	private List<ConcurrentMapCache> getCacheMembers() {
		String[] cacheMembers = env.getProperty("global.cache.members").split(
				",");
		List<ConcurrentMapCache> caches = new ArrayList<ConcurrentMapCache>();
		ConcurrentMapCache cache = null;
		for (String cacheMember : cacheMembers) {
			cache = new ConcurrentMapCache(cacheMember);
			caches.add(cache);
		}
		return caches;

	}

	/**
	 * <code>restTemplate</code> Returns RestTemplate taking it from
	 * ClientHttpRequestFactory Which configures timeouts
	 * 
	 * @return restTemplate instance
	 */

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	/**
	 * <code>clientHttpRequestFactory</code> : Adds Timeouts to connections
	 * 
	 * @return ClientHttpRequestFactory instance configured with timeouts
	 */
	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(Integer.parseInt(env
				.getProperty("global.url.read.timeout")));
		factory.setConnectTimeout(Integer.parseInt(env
				.getProperty("global.url.write.timeout")));
		return factory;
	}
	
	 }

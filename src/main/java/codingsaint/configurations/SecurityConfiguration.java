package codingsaint.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * <code>SecurityConfiguration</code> : Defense System of entire application
 * 
 * @author Kumar Pallav
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.inMemoryAuthentication()
				.withUser(env.getProperty("global.godmode.user"))
				.password(env.getProperty("global.godmode.password"))
				.roles("ADMIN").and().withUser("user").password("user")
				.roles("USER");
		/*
		 * auth.ldapAuthentication(). userDnPatterns("uid={0},ou=people").
		 * groupSearchBase("ou=groups").
		 * contextSource().ldif("classpath:test-server.ldif"
		 * ).and().ldapAuthoritiesPopulator(authoritiesPopulator);
		 */
		// The second example shows how you can override the default queries in
		// order
		// to use your own tables rather than Spring Security's default tables
		// The SQL is relatively simple and should be easy to figure out and map
		// to your needs
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select username,password,enabled from t_security_users where username=?")
				.authoritiesByUsernameQuery(
						"select u.username, r.authority from t_security_users u, t_authorities r where u.username = r.username and u.username =?");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// This is here to ensure that the static content (JavaScript, CSS, etc)
		// is accessible from the login page without authentication
		web.ignoring().antMatchers("/static/**").antMatchers("/js/**")
				.antMatchers("/css/**").antMatchers("/less/**")
				.antMatchers("/img/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

		// access-denied-page: this is the page users will be
		// redirected to when they try to access protected areas.
		.exceptionHandling()
				.accessDeniedPage("/403")
				.and()

				// The intercept-url configuration is where we specify what
				// roles are allowed access to what areas.
				// We specifically force the connection to https for all the
				// pages, although it could be sufficient
				// just on the login page. The access parameter is where the
				// expressions are used to control which
				// roles can access specific areas. One of the most important
				// things is the order of the intercept-urls,
				// the most catch-all type patterns should at the bottom of the
				// list as the matches are executed
				// in the order they are configured below. So /** (anyRequest())
				// should always be at the bottom of the list.
				.authorizeRequests()
				.antMatchers("/login**", "/sso**", "/cs/**")
				.permitAll()
				.antMatchers("/admin/**")
				.hasRole("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				// .requiresChannel()
				// .anyRequest().requiresSecure()
				// .and()

				// This is where we configure our login form.
				// login-page: the page that contains the login screen
				// login-processing-url: this is the URL to which the login form
				// should be submitted
				// default-target-url: the URL to which the user will be
				// redirected if they login successfully
				// authentication-failure-url: the URL to which the user will be
				// redirected if they fail login
				// username-parameter: the name of the request parameter which
				// contains the username
				// password-parameter: the name of the request parameter which
				// contains the password
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login.do")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=1")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()

				// This is where the logout page and process is configured. The
				// logout-url is the URL to send
				// the user to in order to logout, the logout-success-url is
				// where they are taken if the logout
				// is successful, and the delete-cookies and invalidate-session
				// make sure that we clean up after logout
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout=1")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and()
				.

				// The session management is used to ensure the user only has
				// one session. This isn't
				// compulsory but can add some extra security to your
				// application.
				sessionManagement().invalidSessionUrl("/login?time=1")
				.maximumSessions(1).expiredUrl("/login?out=1");
	}

}

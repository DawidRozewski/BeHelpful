package pl.coderslab.charity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.security.UrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/form", "/dashboard").hasRole("USER")
                .and().formLogin()
                .loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler())
                .and().logout()
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");
        http.csrf().disable();

    }

    @Bean
    public CurrentUser currentUser() {
        return new CurrentUser();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new UrlAuthenticationSuccessHandler();
    }




}

package pl.kurs.test4shapes.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
////
//////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("creator").password(passwordEncoder().encode("creator")).roles("CREATOR")
////                .and()
////                .withUser("test").password(passwordEncoder().encode("test")).roles("TEST");
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeRequests()
////                .antMatchers("/api/v1/shapes").hasAnyRole("CREATOR")
////                .anyRequest().permitAll()
////                .and()
////                .httpBasic()
////                .and()
////                .csrf().disable();
////    }
////
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//}

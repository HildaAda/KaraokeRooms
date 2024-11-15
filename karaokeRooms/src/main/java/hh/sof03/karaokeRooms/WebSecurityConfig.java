package hh.sof03.karaokeRooms;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    
    @Autowired
	private UserDetailsService userDetailsService;

	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
      http
      .authorizeHttpRequests( authorize -> authorize
        	.requestMatchers(antMatcher("/roomlist/**")).permitAll()
            .requestMatchers(antMatcher("/css/**")).permitAll()
            .requestMatchers(antMatcher("/adduser/**")).permitAll()
            .requestMatchers(antMatcher("/saveuser/**")).permitAll()
            .requestMatchers(toH2Console()).permitAll()
            .requestMatchers(antMatcher("/rooms")).permitAll()
            .requestMatchers(antMatcher("/rooms/{id}")).permitAll()
            //.requestMatchers(antMatcher("/reservations")).permitAll()
            //.requestMatchers(antMatcher("/reservations/{id}")).permitAll()
        	.anyRequest().authenticated()
      )
      .csrf(csrf -> csrf
          .ignoringRequestMatchers(toH2Console())
     )
     .headers(headers -> headers
         .frameOptions(frameoptions -> frameoptions
                  .disable())
     )
      .formLogin(formlogin -> formlogin
          .defaultSuccessUrl("/roomlist", true)
          .permitAll()
      )
      .logout(logout -> logout
          .logoutSuccessUrl("/roomlist")
          .permitAll()
      );
      return http.build();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}

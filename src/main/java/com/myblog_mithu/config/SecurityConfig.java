package com.myblog_mithu.config;


import com.myblog_mithu.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Only Admin can create post , User cant create post it means. after that u can use @PreAuthorize("hasRole('ADMIN')")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //********************************** SIGN-IN*******************************************
    // U SHOULD CREATE CustomUserDetailsService CLASS INSIDE SecurityConfig CLASS
    // when I create of object of CustomUserDetailsService, the content of the class will go to the object (userDetailsService).
    // what is the content of this class loadUserByUsername(String username) method, what is loadUserByUsername() method returning User object.
    //what is present in User Object - username , password and GrantedAuthority. that's nothing but the role
    // this u need to set that in the config because who is reading the config file - Spring Security
    // all u do in config file - Spring Security reads it.

    // it has accessed of this object ( userDetailsService ) and this object has the details of loadUserByUsername and that loadUserByUsername object has all ur database username and password.
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }




    @Bean
    PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll() // it will open for reading records
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll() // it will open for signup
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


//    // In Memory Authentication
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService(){
//
//        // it automatically creates one objects of the type userDetails1
//        UserDetails userDetails1 = User.builder().username("mithu").password(passwordEncoder().encode("mithu@123")).roles("USER").build();
//
//        // it automatically creates one objects of the type userDetails2
//        UserDetails userDetails2 = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(userDetails1,userDetails2);
//  }


    //********************************** SIGN-IN*******************************************
      // this SecurityConfig class has this method ( configure() )
      // This (AuthenticationManagerBuilder auth) - this will take the user Details basically the password
     // This is the fix rule to build this method in spring security.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // userDetailsService - its a building method which is supplying the ur username and password that came from the database. spring security has this actual username and password and grantedAuthority role with it.
    }

    // Next part -- AuthController






    // ************************************************INTERVIEW QUESTION*****************************************************
    // Have you Implement the Spring Security?
    // Yes Sir
    // How did you Implement?
    // step 1- I have added Spring Security Library.
    // step 2- I have Created user table roles table with ManyToMany mapping.
    // step 3- I have encoded the password and perform the signup
    // step 4- In the config class I have decided which url who can access.
    // step 5- I have developed the signUp feature in the signup feature I developed the LoginDTO which is credential from the user.
    // step 6- I have developed CustomUserDetailsService Class , which is load by username. that takes the username goes to the database and it gets the actual username and password from the database and such that into the user object of spring security.
    // step 7- And then I go to the config file and I will load the CustomUserDetailsService class to springBoot.
    // How will you load it?
    // Sir, By adding this method configure.
    // So, What configure method does?
    // Sir, The configure method takes the details and it comes from the database and its educate springBoot look This is your username and password that coming from the database.
    //







}

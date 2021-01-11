package florie.photofoot.config;

import florie.photofoot.mapper.ActivityMapper;
import florie.photofoot.mapper.UserInfoMapper;
import florie.photofoot.model.Activity;
import florie.photofoot.model.UserInfo;
import florie.photofoot.service.CustomUserDetailsService;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@MappedTypes({UserInfo.class, Activity.class})
@MapperScan("florie.photofoot.mapper")
@EnableWebSecurity
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter{
    public securityConfig(CustomUserDetailsService userDetailsService, ActivityMapper aMapper) {
        this.userDetailsService = userDetailsService;
        this.aMapper = aMapper;
    }

    private ActivityMapper aMapper;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/PhotoFoot/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/Account/Login").permitAll()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                        Activity activity = new Activity();
                        activity.setUsername(userDetails.getUsername());
                        activity.setModified(new Timestamp(System.currentTimeMillis()));
                        activity.setType("Login");
                        aMapper.insert(activity);

                        httpServletResponse.sendRedirect("/PhotoFoot/Home");
                    }
                }).failureUrl("/Account/Login?error");
        http.logout().logoutSuccessHandler(new LogoutSuccessHandler(){
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                Activity activity = new Activity();
                activity.setUsername(userDetails.getUsername());
                activity.setModified(new Timestamp(System.currentTimeMillis()));
                activity.setType("Logout");
                aMapper.insert(activity);

                httpServletResponse.sendRedirect("/Account/Login");
            }
        }).logoutSuccessUrl("/Account/Login");
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                if(charSequence.toString().equals(s)){
                    return true;
                }
                return false;
            }
        };
    }
}

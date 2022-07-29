package com.qf.zlp.framework.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.zlp.framework.entity.Hr;
import com.qf.zlp.framework.entity.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.FileDescriptor;
import java.io.PrintWriter;
@Configuration
public class SecurityConfig {

    //动态接口访问权限
    //1.客户端发送请求：https：//http://localhost:8080/employee/basic/hello
    //2.服务端提取出请求地址/employee/basic/hello，服务端去分析这些请求需要那些权限才能访问,分析的思路就是拿着这个地址去数据库menu表中比对
    //看和哪个菜单能匹配上，找到对应的菜单后，再去menu_role 表中查看这个菜单哪些权限才能访问。




    @Bean
    PasswordEncoder passwordEncoder(){

        return  new BCryptPasswordEncoder();
    }
        @Autowired
        MyAccessDecisionManager myAccessDecisionManager;

        @Autowired
        MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(myAccessDecisionManager);

                        return object;
                    }


                })

                 //所有请求
                .anyRequest()

                //必须认证后才能通过，类似于 shiro 中的authc
                //所有请求必须认证才能访问，但是，一写静态资源：如：css、js，不需要授权，此时就需要antMatchers() 了。
                .authenticated()
                .and()
                // //开始配置登录表单
                .formLogin()
                /*登录成功处理器*/
                //登录成功处理器
                //request 当前请求对象
                //response：当前响应对象
                //authentication :当前登录成功的用户信息

                .successHandler((request, response, authentication) -> {
                    /*设置响应数据类型*/
                    response.setContentType("application/json;charset=utf-8");
                    /*调用 writer方法*/
                    PrintWriter out = response.getWriter();
                    /*拿到前端输入的账户名*/
                    Hr hr = (Hr) authentication.getPrincipal();

                    hr.setPassword(null);

                    RespBean respBean = RespBean.ok("登录成功", hr);

                    out.write(new ObjectMapper().writeValueAsString(respBean));

                })
                /*登录失败处理器*/
                .failureHandler((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");

                    PrintWriter out = response.getWriter();
                    RespBean respBean = RespBean.error("登录失败");
                    if (e instanceof BadCredentialsException) {
                        respBean.setMsg("账户名或密码错误，登录失败");
                    } else if (e instanceof DisabledException) {
                        respBean.setMsg("账户被禁用，登录失败");
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));

                })
                    /*设置响应数据类型*/
                .and()
                //配置注销登录
                .logout()
                //注销地址
                .logoutUrl("/logout")
                //注销成功后跳转地址 new LogoutSuccessHandler()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    /*调用 writer方法*/
                    PrintWriter out = response.getWriter();

                    RespBean respBean = RespBean.ok("注销成功");

                    out.write(new ObjectMapper().writeValueAsString(respBean));


                })
                .and()

                //关闭 csrf 防御机制，这个 disable 方法 本质上就是从 spring security 的过滤链上移除掉 csrf 过滤器
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint((request, response, exception) -> {
                    /*权限拦截401响应码*/
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();

                    RespBean respBean = RespBean.error("尚未登陆请登录");

                    out.write(new ObjectMapper().writeValueAsString(respBean));

                });
        return http.build();
    }








    }











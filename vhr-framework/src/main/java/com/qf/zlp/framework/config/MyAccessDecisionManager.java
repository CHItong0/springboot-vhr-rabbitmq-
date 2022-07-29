package com.qf.zlp.framework.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * 这里主要是判断用户是否具备当前请求所需的权限
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 核心的逻辑，如果用户当前用户权限不满足要求的话，直接抛出即可，如果这个方法正常执行完，那就说明权限是满足的。
     *
     * @param authentication 存放当前用户信息，这里面就包含当前登录的用户信息（包含角色）
     * @param object 这里包含当前请求
     * @param configAttributes 当前请求所需角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */



    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
            //authentication 获取当前登录的用户信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (ConfigAttribute configAttribute : configAttributes) {
            //获取当前用户字符串
            String attribute = configAttribute.getAttribute();

            if ("ROLE_LOGIN".equals(attribute)){
                //说明当前请求登录之后，就可以访问，不需要做权限判断。

                if (authentication instanceof UsernamePasswordAuthenticationToken){
                    //说明当前用户已经登录了
                    return;
                }else {
                    //未登录
                    throw new AccessDeniedException("权限不足，请联系管理员");
                }
            }
            for (GrantedAuthority authority : authorities) {

                if (authority.getAuthority().equals(attribute)){
                    //具备相应的权限
                    return;
                }


            }
            //如果没有return，说明当前用户不具备当前请求的权限
        throw new AccessDeniedException("权限不足，请联系管理员");

        }
    }








    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

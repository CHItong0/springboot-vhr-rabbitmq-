package com.qf.zlp.framework.config;

import com.qf.zlp.framework.entity.Role;
import com.qf.zlp.framework.entity.vo.MenuRoleVo;
import com.qf.zlp.framework.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 这个接口的作用提取出请求地址 /employee/basic/hello，服务端去分析这个地址需要哪些角色才能访问，
 * 分析的思路，就是拿着这个地址去服务端，到数据库 menu 表中去比对，找到对应的菜单后，再去menu_role中去查看
 * 这个菜单，需要哪些角色能访问。
 * *
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

        @Autowired
        IMenuService menuService;

        //ant风格的路径比对器
        AntPathMatcher antPathMatcher =new AntPathMatcher();
    /**
     * 最核心的判断方法
     * 获取某个受保护的安全对象object的所需要的权限信息,是一组ConfigAttribute对象的集合，
     * 如果该安全对象object不被当前SecurityMetadataSource对象支持,则抛出异常IllegalArgumentException。
     * 该方法通常配合boolean supports(Class<?> clazz)一起使用，先使用boolean supports(Class<?> clazz)
     * 确保安全对象能被当前SecurityMetadataSource支持，然后再调用该方法
     *
     * @param object 这个参数是被拦截下来的对象，这个对象就包含请求地址
     * @return 返回值就是当前这个地址所需要的的角色
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //强转object 为FilterInvocation 获取当前请求地址
        String requestUrl = ((FilterInvocation) object).getFullRequestUrl();

        //获取当前所有角色与其能访问的菜单
        List<MenuRoleVo> menuAllRole = menuService.getMenuAllRole();

        for (MenuRoleVo menuRoleVo : menuAllRole) {
            //路径比对
            //第一个参数是规则，数据库里面的
            //第二个是 拦截下来的
            if (antPathMatcher.match(menuRoleVo.getUrl(),requestUrl)){
                //如果匹配上了，说明这个地址找到了对应的菜单项。，进而就能找到这个请求所需要的对应角色。
                List<Role> roles = menuRoleVo.getRoles();

                String[] rolesArr =new String [roles.size()];
                int index=0;

                for (Role role : roles) {

                rolesArr[index++]=role.getName();

                }
                return SecurityConfig.createList(rolesArr);

            }
        }
        //如果一个请求地址和数据库中的所有菜单项都没有匹配上，那么就说明这个地址登录就可以访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    /**
     * 获取该SecurityMetadataSource对象中保存的针对所有安全对象的权限信息的集合。
     * 该方法的主要目的是被AbstractSecurityInterceptor用于启动时校验每个ConfigAttribute对象。
     * @return
     */

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 这里clazz表示安全对象的类型，该方法用于告知调用者当前SecurityMetadataSource是否支持此类安全对象，
     * 只有支持的时候，才能对这类安全对象调用getAttributes方法。
     * @param clazz 这里clazz表示安全对象的类型
     * @return
     */

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

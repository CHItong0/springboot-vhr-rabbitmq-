package com.qf.zlp.admin.controller;

import com.qf.zlp.framework.entity.vo.MenuVO;
import com.qf.zlp.framework.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    IMenuService menuService;

    @GetMapping("/menus")
    public List<MenuVO> getAllMenus(){
        
        return menuService.getAllMenus();
    }
    
}

package org.geekbang.thinking.in.spring.ioc.bean.scope.web.controller;

import org.geekbang.thinking.in.spring.ioc.abs.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author riku
 * @Classname IndexController
 * @Date 2020/12/28 2:08
 * @Description 首页 Spring Web MVC Controller
 */
@Controller
public class IndexController {

    @Autowired
    private User user;

    /**
     * JSP EL 变量搜索路径： page -> request -> session -> application
     * @param model
     * @return
     */
    @GetMapping("/index.html")
    public String index(Model model) {
        // userObject 存在于 渲染上下文
        // user 对象 存在于 ServletContext 上下文
        model.addAttribute("userObject", user);
//        model.addAttribute("user", user);

        return "index";
    }

    @GetMapping("/chage.html")
    public String change(Model model) {
        // userObject 存在于 渲染上下文
        // user 对象 存在于 ServletContext 上下文
        user.setName("riku2");
//        model.addAttribute("user", user);

        return "index";
    }
}

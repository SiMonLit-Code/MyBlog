package com.czz.web.admin;

import com.czz.po.User;
import com.czz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 管理员账户验证
 */
@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
       /* User user = new User() ;
        user.setUsername(username);
        user.setPassword("123456");
        if (username!=null){
            session.setAttribute("user",username);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }*/
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}

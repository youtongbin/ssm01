package com.neuedu.web;

import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WebTest {
    @Autowired
    private IUserService userService;

    @RequestMapping("/test.do")
    public String test(){
        return "test";
    }

    @RequestMapping("/list.do")
    public String list(ModelMap map){
        List<User> users = userService.getLists();
        map.put("users",users);
        return "list";
    }

    @RequestMapping("/insert.do")
    public String insert(){
        return "insert";
    }
    @RequestMapping("/doInsert.do")
    public String doInsert(User user){
        if (!user.getUsername().matches("[ ]*") && !user.getPassword().matches("[ ]*")) {
            userService.insert(user);
        }else {
            return "redirect:insert.do";
        }
        return "redirect:list.do";
    }

    @RequestMapping("/update.do")
    public String update(ModelMap map,Integer id){
        map.put("user",userService.getOne(id));
        return "update";
    }
    @RequestMapping("/doUpdate.do")
    public String doUpdate(ModelMap map,User user,String passwords){
        if (!user.getUsername().matches("[ ]*")
                && !user.getPassword().matches("[ ]*")
                && !passwords.matches("[ ]*")){
            if (passwords.equals(user.getPassword())){
                userService.update(user);
            }
        }
        return "redirect:list.do";
    }

    @RequestMapping("/delete.do")
    public String delete(ModelMap map,Integer id){
        userService.delete(id);
        return "redirect:list.do";
    }

}

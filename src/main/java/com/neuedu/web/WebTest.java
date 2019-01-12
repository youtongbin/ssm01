package com.neuedu.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import com.neuedu.util.CookieUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class WebTest {
    @Autowired
    private IUserService userService;

    @RequestMapping("/test.do")
    public String test(){
        return "test";
    }

    @RequestMapping("/list.do")
    public String list(ModelMap map,HttpServletRequest req,User user){
        int pageNum = req.getParameter("pageNum") == null ? 1 : Integer.parseInt(req.getParameter("pageNum"));
        int pageSize = 2;
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = null;

        String username = user.getUsername();
        if (StringUtils.isBlank(username)){
            user.setUsername(null);
            users = userService.getLists(user);
        }else {
            users = userService.getLists(user);
            map.put("username","&username=" + username);
        }
        PageInfo<User> page = new PageInfo<>(users,3);
        map.put("users",users);
        map.put("page",page);
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

    @RequestMapping("/login.do")
    public String login(HttpServletRequest req,ModelMap map){
        Cookie[] cookies = req.getCookies();
        Map<String,Cookie> cookieMap = CookieUtil.getCookie(cookies);
        Cookie usernameCoo = cookieMap.get("username");
        Cookie passwordCoo = cookieMap.get("password");
        if (usernameCoo != null){
            map.put("username",usernameCoo.getValue());
        }
        if (passwordCoo != null){
            map.put("password",passwordCoo.getValue());
        }

        return "login";
    }

    @RequestMapping("/doLogin.do")
    public String doLogin(HttpServletRequest req,HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String save = req.getParameter("save");
        User u = userService.getOne(username);

        if (!username.matches("[ ]*") && !password.matches("[ ]*")){
            if (u != null){
                if (password.equals(u.getPassword())){
                    Cookie cookie1 = new Cookie("username",username);
                    Cookie cookie2 = new Cookie("password",password);

                    cookie1.setMaxAge(60*60*24*7);
                    resp.addCookie(cookie1);
                    if (save.equals("Save")){
                        cookie2.setMaxAge(60*60*24*7);
                        resp.addCookie(cookie2);
                    }else if (save.equals("noSave")){
                        cookie2.setMaxAge(0);
                        resp.addCookie(cookie2);
                    }

                    HttpSession session = req.getSession();
                    session.setAttribute("user",u);
                    session.setAttribute("save",save);
                    session.setMaxInactiveInterval(60*30);

                    return "redirect:list.do";
                }else {
                    //密码错误
                }
            }else {
                //没有该用户
            }
        }else {
            //用户名、密码输入为空
        }

        return "login";
    }

    @RequestMapping("/exit.do")
    public String exit(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:login.do";
    }

    @RequestMapping("/doUpload.do")
    public String doUpload(@RequestParam("file") MultipartFile[] files){

        for (MultipartFile f:files
             ) {
            if (!f.isEmpty()){
                String filename = f.getOriginalFilename();
                try {
                    FileUtils.copyInputStreamToFile(f.getInputStream(),new File("C:\\gitHub\\ssm01\\src\\main\\webapp\\files\\img\\" + filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "redirect:list.do";
    }

    @RequestMapping("/register.do")
    public String register(){
        return "register";
    }

    @ResponseBody
    @RequestMapping(value = "/doRegisterCheck.do",method = RequestMethod.POST)
    private String doRegisterCheck(@RequestBody String jsonStr){
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        String username = (String) jsonObj.get("username");
        String password = (String) jsonObj.get("password");
        String passwords = (String) jsonObj.get("passwords");
        String tele = (String) jsonObj.get("tele");

        System.out.println(new StringBuffer().append(username).append(" " + password).append(" " + passwords).append(" " + tele));

        User user = userService.getOne(username);
        if (!username.matches("[ ]*")){
            if (user == null){
                if (!password.matches("[ ]*")){
                    if (password.equals(passwords)){
                        //验证通过
                        System.out.println("验证通过");
//                        userService.insert(new User(username,password,tele));
                        return "success";
                    }else {
                        //密码验证失败
                        System.out.println("密码验证失败");
                        return "passwordFail";
                    }
                }else {
                    //密码输入为空
                    System.out.println("密码输入为空");
                    return "passwordIsNull";
                }
            }else {
                //用戶名已存在
                System.out.println("用戶名已存在");
                return "userExists";
            }
        }else {
            //用户名输入为空
            System.out.println("用户名输入为空");
            return "usernameIsNull";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/doRegister.do",method = RequestMethod.POST)
    public String doRegister(@RequestBody String jsonStr){
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);

        userService.insert(new User((String) jsonObj.get("username"),(String) jsonObj.get("password"),(String) jsonObj.get("tele")));
        return "success";

    }

}

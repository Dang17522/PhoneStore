package com.edu.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.model.Authority;
import com.edu.model.Role;
import com.edu.model.User;
import com.edu.service.AuthorityService;
import com.edu.service.RoleService;
import com.edu.service.UserService;
import com.edu.service.UserServices;

@Controller
@EnableWebSecurity
public class SecurityController {

    private final String folder_image = "src/main/resources/static/assets/images/";
    @Autowired
    private ServletContext application;

    @Autowired
    HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private RoleService roleService;
    
    

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/security/login/form")
    public String loginform(Model model) {
        return "security/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công");
        List<Authority> list = authorityService.list(request.getRemoteUser());
        for (Authority authority : list) {
            if(authority.getRole().getId().equals("DIRE") || authority.getRole().getId().equals("STAF")) {
                return "redirect:/admin/home/index";
            }
        }
        return "redirect:/PhoneStore";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập");
        return "security/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "không có quyền truy xuất");
        return "security/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Đăng xuất thành công");
        return "security/login";
    }

    @GetMapping("/login/register")
    public String registerindex() {
        return "security/register";
    }

    @RequestMapping("/login/register")
    public String register(HttpServletRequest request, ModelMap model) {
        String p1 = request.getParameter("password");
        String p2 = request.getParameter("password2");
        Optional<User> u1 = userService.findByOPUsername(request.getParameter("username"));
        Optional<User> u2 = userService.findByOPEmail(request.getParameter("email"));
        if (u1.isPresent()) {
            model.addAttribute("message", "UserName đã tồn tại");
            return "security/register";
        } else {
            if (u2.isPresent()) {
                model.addAttribute("message", "Email đã tồn tại");
                return "security/register";
            } else {
                if (p1.equals(p2)) {
                    User acc = new User();
                    Date date = new Date();
                    Authority au = new Authority();
                    acc.setUsername(request.getParameter("username"));
                    acc.setPassword(request.getParameter("password"));
                    acc.setEmail(request.getParameter("email"));
                    acc.setPhoto("");
                    acc.setCreateDate(date);
                    acc.setFullname(request.getParameter("username"));
                    acc.setOrders(null);
                    acc.setAuthorites(null);
                    userService.save(acc);

                    Authority a = new Authority();
                    Role r = roleService.findById("CUST").get();
                    a.setUser(acc);
                    a.setRole(r);
                    authorityService.create(a);
                    model.addAttribute("message", "Đăng kí thành công");
                    return "security/login";
                }
            }
        }

        return "security/register";

    }

    
    @RequestMapping("/oauth2/login/failure")
    public String oauth2LoginFailure(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "forward:/login/form";
    }

    @RequestMapping("/security/login/chang")
    public String chang(Model model) {
        return "security/chang";
    }

    @PostMapping("/security/login/chang")
    public String chang(HttpServletRequest request, ModelMap model, @RequestParam("username") String username) {
        User acc = userService.findID(request.getParameter("username"));
        if (acc.getPassword().equals(request.getParameter("password"))) {
            if (request.getParameter("password1").equals(request.getParameter("password2"))) {
                String password1 = request.getParameter("password1");
                userService.changePassword(password1, username);
                model.addAttribute("message", "Đổi Mật Khẩu Thành Công");
                return "security/login";
            } else {
                model.addAttribute("message", "2 Mật khẩu không khớp");
            }
        } else {
            model.addAttribute("message", "Mật khẩu không đúng");
        }

        return "security/chang";

    }

    @GetMapping("/security/login/editprofile")
    public String editprofile(Model model) {
        User u = userService.findByUsername(request.getRemoteUser());
        model.addAttribute("fullname", u.getFullname());
        model.addAttribute("email", u.getEmail());
        model.addAttribute("phone", u.getPhone());
        return "security/editprofile";
    }

    @PostMapping("/security/login/editprofile")
    public String updateeditprofile(@ModelAttribute("accountRequest") User userRequest,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam("username") String username, Model model) {
        try {
            if (imageFile.getSize() == 0) {
                userService.updateNonImage(userRequest.getEmail(), userRequest.getFullname(), userRequest.getPhone(),
                        userRequest.getUsername());
            } else {
                userRequest.setPhoto(imageFile.getOriginalFilename());
                String filepath = "D:/eclipse/DATN/" + folder_image + userRequest.getPhoto();
                File file = new File(filepath);
                imageFile.transferTo(file);
                userService.update(userRequest);
            }
            redirectAttributes.addFlashAttribute("mess",
                    "Tài khoản " + userRequest.getUsername() + " cập nhật thành công");

            session.setAttribute("image", userService.findID(username).getPhoto());

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mess",
                    "cập nhật thất bại" + userRequest.getUsername());
        }
        User u = userService.findByUsername(request.getRemoteUser());
        model.addAttribute("fullname", u.getFullname());
        model.addAttribute("email", u.getEmail());
        model.addAttribute("phone", u.getPhone());
        return "redirect:/security/login/editprofile";
    }
}

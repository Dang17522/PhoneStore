package com.edu.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.Utils.Utility;
import com.edu.model.User;
import com.edu.service.UserService;

import net.bytebuddy.utility.RandomString;


@Controller
public class ForgotPasswordController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailsender;

	@GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("pageTitle", "Fogot Password");
        return "security/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            System.out.println(token);
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Chúng tôi đã gửi một liên kết đặt lại mật khẩu đến email của bạn. Hãy kiểm tra.");
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Lỗi khi gửi email.");
            e.printStackTrace();
        }
        model.addAttribute("pageTitle", "Quên mật khẩu");
        return "security/forgot_password_form";
    }

    private void sendEmail(String email, String resetPasswordLink)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("haidang17522@gmail.com", "PhoneStore Hỗ trợ");
        helper.setTo(email);

        String subject = "Đây là liên kết để đặt lại mật khẩu của bạn";
        String content = "<p>Xin chào,</p>" + "<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
                + "<p>Nhấp vào liên kết bên dưới để thay đổi mật khẩu của bạn:</p>" + "<p><b><a href=\"" + resetPasswordLink
                + "\">Thay đổi mật khẩu của tôi</a></b></p>" + "<br>" + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình, "
                + "hoặc bạn chưa thực hiện yêu cầu.</p>";
        helper.setSubject(subject);

        helper.setText(content, true);

        mailsender.send(message);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value="token") String token, Model model) {
        User account = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Đặt lại mật khẩu của bạn");
        if(account == null) {
            model.addAttribute("message", "Mã không hợp lệ");
            model.addAttribute("title", "Đặt lại mật khẩu của bạn");
            return "security/message";
        }
        return "security/reset_password_form";

    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
         
        User account = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Đặt lại mật khẩu của bạn");
         
        if (account == null) {
            model.addAttribute("message", "Mã không hợp lệ");
            return "message";
        } else {
            userService.changePassword(password, account.getUsername());
            model.addAttribute("message", "Bạn đã thay đổi thành công mật khẩu của bạn.");
        }
         
        return "security/message";

    }
}

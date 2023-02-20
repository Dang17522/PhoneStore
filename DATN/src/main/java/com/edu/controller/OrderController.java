package com.edu.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.Utils.SessionUtil;
import com.edu.model.Order;
import com.edu.model.OrderDetail;
import com.edu.model.OrderTrack;
import com.edu.model.User;
import com.edu.service.OrderDetailService;
import com.edu.service.OrderService;
import com.edu.service.OrderTrackService;
import com.edu.service.UserService;

@Controller
public class OrderController {
    @Autowired
    OrderService orderservice;

    @Autowired
    JavaMailSender mailsender;

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderTrackService orderTrackService;
    
    @Autowired
    SessionUtil session;

    @RequestMapping("/order/checkout")
    public String checkout(Model model) {
        String phone = userService.findID(request.getRemoteUser()).getPhone();
        session.setAttribute("phone", phone);
        return "order/checkout";
    }

    @RequestMapping("/order/list")
    public String list(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderservice.findUsername(username));
        return "order/list";
    }

    @RequestMapping("/order/list_fail")
    public String listfail(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderservice.findUsernamefail(username));
        return "order/list_fail";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        double totolprice = 0;
        List<OrderDetail> detail = orderDetailService.findByOrderIdlist(id);
        for (int i = 0; i < detail.size(); i++) {
            detail.get(i)
                    .setTotal((detail.get(i).getQuantity())
                            * (detail.get(i).getPrice()
                                    - (detail.get(i).getPrice()
                                            * detail.get(i).getProduct().getDiscount().getNumber())));
            detail.get(i).getProduct().setQuantity(detail.get(i).getProduct().getQuantity() - 1);
            totolprice += detail.get(i).getTotal();

        }

        model.addAttribute("total", totolprice + 30000);
        model.addAttribute("order", orderservice.findId(id));
        return "order/detail";
    }

    @RequestMapping("/order/details/{id}")
    public String details(@PathVariable("id") Long id, Model model)
            throws UnsupportedEncodingException, MessagingException {
        double totolprice = 0;
        model.addAttribute("order", orderservice.findId(id));
        List<OrderDetail> detail = orderDetailService.findByOrderIdlist(id);
        User user = userService.findID(request.getRemoteUser());
        for (int i = 0; i < detail.size(); i++) {
            detail.get(i)
                    .setTotal((detail.get(i).getQuantity())
                            * (detail.get(i).getPrice()
                                    - (detail.get(i).getPrice()
                                            * detail.get(i).getProduct().getDiscount().getNumber())));
            detail.get(i).getProduct().setQuantity(detail.get(i).getProduct().getQuantity() - 1);
            totolprice += detail.get(i).getTotal();

        }
        model.addAttribute("total", totolprice + 30000);
        Order order = orderservice.findId(id);
        Integer voucher = session.getAttribute("giamgia");
        order.setTotalprice(totolprice + 30000 - voucher);

        order.setDiscount(session.getAttribute("giamgia"));
        orderservice.save(order);
        String email = user.getEmail();
        Long idorder = order.getId();
        if (user.getEmail() != null) {
            sendEmail(email, idorder);
        }
        orderDetailService.saveAll(detail);
        session.setAttribute("giamgia", 0);
        return "order/detail";
    }

    private void sendEmail(String email, Long idorder)
            throws UnsupportedEncodingException, MessagingException {
        Order order = orderservice.findId(idorder);

        MimeMessage message = mailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("haidang17522@gmail.com", "PhoneStore");
        helper.setTo(email);
        String subject = "Thông tin đơn hàng";
        String content = "-------------------------------------------------------------------------------------------------"
                + "<br>| Xin chào, Hóa đơn của bạn: " + order.getId()
                + "<br>| Ngày: " + order.getCreateDate()
                + "<br>| Số điện thoại: " + order.getPhone()
                + "<br>| Địa chỉ: " + order.getAddress() + " " + order.getDistrict() + " " + order.getProvince()
                + "<br>| Nhấn vào để xem chi tiết hóa đơn"
                + "<br>-------------------------------------------------------------------------------------------------"
                + "<br><a href=\"http://localhost:8080/order/details/" + order.getId()
                + "\">Xem chi tiết</a>";
        ;

        helper.setSubject(subject);

        helper.setText(content, true);

        mailsender.send(message);
    }

    private void sendEmailCancle(String email, Long idorder)
            throws UnsupportedEncodingException, MessagingException {
        Order order = orderservice.findId(idorder);

        MimeMessage message = mailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("haidang17522@gmail.com", "PhoneStore");
        helper.setTo(email);

        String subject = "Thông tin đơn hàng";

        String content = "-------------------------------------------------------------------------------------------------"
                + "<br>| Xin chào, Hóa đơn đã hủy: " + order.getId()

                + "<br>| Chúc các bạn một ngày tốt lành, hẹn gặp lại"

                + "<br>| Nhấn để xem chi tiết"
                + "<br>-------------------------------------------------------------------------------------------------"
                + "<br><a href=\"http://localhost:8080/order/details/" + order.getId()
                + "\">Xem chi tiết</a>";
        ;

        helper.setSubject(subject);

        helper.setText(content, true);

        mailsender.send(message);
    }

    @RequestMapping("/order/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)
            throws UnsupportedEncodingException, MessagingException {
        Order o = orderservice.findById(id).get();
        OrderTrack orderTrack = orderTrackService.findById((long) 4).get();
        o.setOrdertrack(orderTrack);
        orderservice.save(o);
        List<OrderDetail> detail = orderDetailService.findByOrderIdlist(id);
        for (int i = 0; i < detail.size(); i++) {
            detail.get(i).getProduct().setQuantity(detail.get(i).getProduct().getQuantity() + 1);
        }
        orderDetailService.saveAll(detail);

        User user = userService.findID(request.getRemoteUser());
        String email = user.getEmail();
        Order order = orderservice.findId(id);
        Long idorder = order.getId();
        if (user.getEmail() != null) {
            sendEmailCancle(email, idorder);
        }
        return "forward:/order/list";
    }

    @RequestMapping("/cart/view")
    public String view(Model model) {

        session.setAttribute("giamgia", 0);
        return "cart/view";
    }
}

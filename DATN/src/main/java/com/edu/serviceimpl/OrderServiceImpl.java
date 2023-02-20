package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Utils.SessionUtil;
import com.edu.model.Order;
import com.edu.model.OrderDetail;
import com.edu.model.OrderTrack;
import com.edu.model.User;
import com.edu.reponsitory.OrderReponsitory;
import com.edu.reponsitory.OrderTrackReponsitory;
import com.edu.reponsitory.OrderdatailReponsitory;
import com.edu.service.OrderService;
import com.edu.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderReponsitory orderReponsitory;
    
    @Autowired
    HttpServletRequest request;
    
    @Autowired
    UserService userService;
    
    @Autowired
    OrderdatailReponsitory orderdatailReponsitory;
    
    @Autowired
    OrderTrackReponsitory orderTrackReponsitory;
    
    @Autowired
    SessionUtil session;
    @Override
    public <S extends Order> S save(S entity) {
        return orderReponsitory.save(entity);
    }

    @Override
    public List<Order> findAll() {
        return orderReponsitory.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderReponsitory.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderReponsitory.deleteById(id);
    }

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData, Order.class);
        User user = userService.findID(request.getRemoteUser());
        if(session.getAttribute("thanhtoan") == "Đả Thanh Toán") {
            order.setStatus(true);
        }else {
            order.setStatus(false);
        }
        
        if(order.getPhone() == null) {
            order.setPhone(user.getPhone());
        }
        orderReponsitory.save(order);
        session.setAttribute("thanhtoan", "");
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> deteils = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderdatailReponsitory.saveAll(deteils);
        return order;
    }

    @Override
    public List<Order> findUsername(String username) {
        return orderReponsitory.findUsername(username);
    }

    @Override
    public Order findId(Long id) {
        return orderReponsitory.findById(id).get();
    }

    @Override
    public List<Order> findUsernamefail(String username) {
        return orderReponsitory.findUsernamefail(username);
    }

    @Override
    public List<Order> findIdArraylist(Long id) {
        return orderReponsitory.findbyIdArraylist(id);
    }
    
    
}

package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.xxl.feign.clients.UserClient;
import cn.xxl.feign.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.用openfeign调用用户服务
        User user = userClient.findById(order.getUserId());
        // 3.封装
        order.setUser(user);
        // 4.返回
        return order;
    }

    // @Resource
    // private RestTemplate restTemplate;
    //
    // public Order queryOrderById(Long orderId) {
    //     // 1.查询订单
    //     Order order = orderMapper.findById(orderId);
    //     // 2.查询用户
    //     User user = restTemplate.getForObject("http://user-service/user/" + order.getUserId(), User.class);
    //     // 3.封装
    //     order.setUser(user);
    //     // 4.返回
    //     return order;
    // }
}

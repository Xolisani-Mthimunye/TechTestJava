package za.co.anycompany.anycompany.service;


import org.springframework.stereotype.Service;
import za.co.anycompany.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.anycompany.datalayer.OrderRepository;
import za.co.anycompany.anycompany.model.Customer;
import za.co.anycompany.anycompany.model.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);

        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() != "UK")
            order.setVAT(0);
        else
            order.setVAT(0.2d);

        orderRepository.save(order);

        return true;
    }
    public Order remove(Integer id) {
    return orderRepository.remove(id);
}

    public List<Order> getAllOrders() {return orderRepository.getAll();}

    public Order getOrderById(int id) {return orderRepository.findById(id);}

    public List<Order> getOrderByCustomerId(int id) {return orderRepository.getOrdersByCustomerId(id);}
}

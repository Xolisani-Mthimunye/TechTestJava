package za.co.anycompany.service;


import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    private CustomerRepository customerRepository = new CustomerRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = customerRepository.load(customerId);

        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() == "UK")
            order.setVAT(0.2d);
        else
            order.setVAT(0);

        orderRepository.save(order);

        return true;
    }
    public Order remove(Integer id) {
    return orderRepository.remove(id);
}

    public List<Order> getAllOrders() {return orderRepository.getAll();}

    public Order getOrderById(int id) {
        Order order = new Order() ; //= orderRepository.findById(id);
        if(id<0){
            return null;
        }

        if(id==0){
            return null;
        }
        if(id>0){
            order = orderRepository.findById(id);
            if (0 != order.getOrderId()){
                return order;
            }
            else {
                return null;
            }
        }
        return order;
    }

    public List<Order> getOrderByCustomerId(int id) {return orderRepository.getOrdersByCustomerId(id);}

    public List<Integer> getAllCustomersWithOrders() {
        return orderRepository.getAllCustomer();
    }
}

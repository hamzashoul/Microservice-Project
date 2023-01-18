package com.project.orderservice;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {



    private static final Logger logger=LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    private Order order;

    @RequestMapping(method = RequestMethod.GET,value = "/customers")
    public List<Client> getCustomers(){
        return orderService.getCustomers();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/products")
    public List<Product> getProducts(){
        return orderService.getProducts();
    }

    @RequestMapping(method = RequestMethod.GET,value = "{id}/{clientId}/{productId}/{quantity}")
    public Order purchase(@PathVariable Long id,@PathVariable Long clientId,@PathVariable Long productId,@PathVariable int quantity){
        if((clientId== orderService.findClient(clientId).getId())&& productId== orderService.findProduct(productId).getId()){
            order.setId(id);
            order.setBuyer(orderService.findClient(clientId).getName());
            order.setProduct(orderService.findProduct(productId).getName());
            order.setQuantity((int) (orderService.findProduct(productId).getPrice()*quantity));
            System.out.println("L'order"+order.toString());
            return order;
        }
        else {
            logger.info("client or/and product not found");
            return null;
        }
    }

    //Get all orders
    @RequestMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    //Get a single client by id
    @RequestMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    //Post request to add order
    @RequestMapping(method = RequestMethod.POST, value= "/orders")
    public void addOrder(@RequestBody Order order){
        orderService.addOrder(order);
    }

    //PUT request to update order
    @RequestMapping(method = RequestMethod.PUT,value = "orders/{id}")
    public void updateOrder(@PathVariable Long id,@RequestBody Order order){
        orderService.updateOrder(id,order);
    }

    //DELETE request to delete order
    @RequestMapping(method = RequestMethod.DELETE, value = "orders/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }



}

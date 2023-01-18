package com.project.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Client client;

    @Autowired
    private Product product;

    @Autowired OrderRepository orderRepository;


    public List<Client> getCustomers(){
            List<Client> customers;
            customers=restTemplate.getForObject("http://clientservice/clients",List.class);
            System.out.println("La liste des clients est:\n"+customers);
            return customers;
    }

    public List<Product> getProducts(){
        List<Product> products;
        products=restTemplate.getForObject("http://productservice/products",List.class);
        System.out.println("La liste des produits est:\n"+products);
        return products;
    }

    public Client findClient(Long id){
        client=restTemplate.getForObject("http://clientservice/clients/"+id,Client.class);
        return client;
    }

    public Product findProduct(Long id){
        product=restTemplate.getForObject("http://productservice/products/"+id,Product.class);
        return product;
    }

    public List<Order> getAllOrders(){
        List<Order> orders=new ArrayList<>();
        orderRepository.findAll()
                .forEach(orders::add);
        return orders;
    }

    public Order getOrderById(Long id){
        return orderRepository.findOrderById(id);
    }

    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public void updateOrder(Long id, Order order){
        order=getOrderById(id);
        orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }



}

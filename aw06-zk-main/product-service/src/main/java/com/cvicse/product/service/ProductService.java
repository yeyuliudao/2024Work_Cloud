package com.cvicse.product.service;


import com.cvicse.product.model.Product;
import com.cvicse.product.repository.ProductRepository;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 假设这是一个远程服务调用的方法，但在这里我们用模拟数据代替
    private Product fetchProductDetailsFromRemoteService(Long id) {
        if (id == null || id < 1) {
            throw new RuntimeException("Invalid product ID");
        }
        // 正常情况下，这里应该是调用远程服务获取数据的逻辑
        // 但为了示例，我们直接返回一个硬编码的Product对象
        return new Product(id, "Product Name: " + id, "Product Description: " + id);
    }

    // 使用@HystrixCommand注解标记的方法，该方法会被Hystrix断路器保护
    public Product getProductById(Long id) {
        // 调用远程服务或数据库以获取产品详情
        return fetchProductDetailsFromRemoteService(id);
    }

    // 断路器回退方法
    // 注意：回退方法的参数必须与原始方法匹配（除了Throwable），或者你可以省略不匹配的参数
    public Product fallbackGetProductById(Long id) {
        // 当原始方法调用失败时，执行此回退逻辑
        // 这里可以记录日志、发送告警等
        return new Product(id, "Fallback Product Name: " + id, "Product not available due to service outage");
    }

    public List<com.cvicse.product.model.Product> findAll() {
        List<com.cvicse.product.model.Product>  a = new ArrayList<>();
        return a;
    }

    // 如果你需要访问触发回退的异常信息，可以添加Throwable参数
    // 但在这个简单的例子中，我们不需要它
    // public Product fallbackGetProductById(Long id, Throwable throwable) {
    //     // 使用throwable参数进行日志记录或异常处理
    //     return new Product(...);
    // }

    // Product类（如果还没有的话）
    public static class Product {
        private Long id;
        private String name;
        private String description;

        public Product(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        // getters and setters
    }
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return (Product) productRepository.findById(id).orElse(null);
    }

}
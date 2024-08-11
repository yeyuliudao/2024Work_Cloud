package com.cvicse.product.repository;
import com.cvicse.product.model.Product;
import com.cvicse.product.service.ProductService;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductService.Product> findAll();

    Optional<Object> findById(Long id);
    // 可以定义一些自定义的查询方法，但JpaRepository已经提供了很多基础方法
    // 例如：findAll(), findById(Long id), save(Product product) 等
}
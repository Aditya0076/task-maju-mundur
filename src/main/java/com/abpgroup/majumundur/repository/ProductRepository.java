package com.abpgroup.majumundur.repository;

import com.abpgroup.majumundur.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

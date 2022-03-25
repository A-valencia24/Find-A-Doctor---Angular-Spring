package com.training.pms.dao;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Product;

public interface ProductDAO extends CrudRepository<Product, Integer> {

	
}

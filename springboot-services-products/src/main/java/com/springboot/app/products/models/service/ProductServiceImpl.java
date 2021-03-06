package com.springboot.app.products.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.app.products.models.entity.Product;
import com.springboot.app.products.models.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductsService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true) //
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

}

package com.springboot.app.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.products.models.entity.Product;
import com.springboot.app.products.models.service.IProductsService;

@RestController
public class ProductController {
	
	@Autowired
	private IProductsService productService;
	
	@GetMapping("/tolist")
	public List<Product> toList() {
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public Product detail(@PathVariable("id") Long id) {
		return productService.findById(id);
	}
}

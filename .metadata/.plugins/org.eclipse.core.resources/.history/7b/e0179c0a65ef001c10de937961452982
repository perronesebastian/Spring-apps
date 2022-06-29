package com.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.app.item.models.Product;


@FeignClient(name = "services-products", url="localhost:8001")
public interface ProductClientRest {
	
	@GetMapping("/tolist")
	public List<Product> toList();
	
	@GetMapping("/{id}")
	public Product detail(@PathVariable("id") Long id);
}

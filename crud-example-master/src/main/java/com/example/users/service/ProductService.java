package com.example.users.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.users.dto.ProductDto;
import com.example.users.entity.ProductEntity;
import com.example.users.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public void create(ProductDto request) {
		ProductEntity entity = new ProductEntity();
		entity.setQuantity(request.getQuantity());
		entity.setDescription(request.getDescription());
		entity.setPrice(request.getPrice());
		productRepository.save(entity);
	}
	
	public void update(Long id, ProductDto product) {
		Optional<ProductEntity> productEntity = productRepository.findById(id);
		if (productEntity.isPresent()) {
			ProductEntity result = productEntity.get();
			result.setQuantity(product.getQuantity());
			result.setDescription(product.getDescription());
			result.setPrice(product.getPrice());
			productRepository.save(result);
		}
	}
	
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	
	public ProductDto get(Long id) {
		Optional<ProductEntity> productEntity = productRepository.findById(id);
		if (productEntity.isPresent()) {
			ProductEntity result = productEntity.get();
			ProductDto productDto = new ProductDto();
			productDto.setQuantity(result.getQuantity());
			productDto.setDescription(result.getDescription());
			productDto.setPrice(result.getPrice());
			return productDto;
		}
		return null;
	}
	
}

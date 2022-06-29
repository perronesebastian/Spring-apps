package com.example.utils;

import com.example.users.dto.ProductDto;
import com.example.users.entity.ProductEntity;

public class MapperProduct {
	
	public ProductDto toDto(ProductEntity productEntity) {
		ProductDto productDto = new ProductDto();
		productDto.setDescription(productEntity.getDescription());
		productDto.setPrice(productEntity.getPrice());
		productDto.setQuantity(productEntity.getQuantity());
		return productDto;
	}
	
	public ProductEntity toEntity(ProductDto productDto, ProductEntity productEntity) {
		productEntity.setDescription(productDto.getDescription());
		productEntity.setPrice(productDto.getPrice());
		productEntity.setQuantity(productDto.getQuantity());
		return productEntity;
	}
}

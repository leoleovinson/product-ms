package xyz.mynt.singson.productms.service;

import java.util.List;

import xyz.mynt.singson.productms.DTO.ProductDTO;
import xyz.mynt.singson.productms.DTO.ProductsDTO;
import xyz.mynt.singson.productms.DTO.ResponseDTO;
import xyz.mynt.singson.productms.entity.ProductEntity;

public interface ProductService {
	
	ProductsDTO getProducts();
	ProductDTO getProduct(Long productId);
	ResponseDTO addProduct(ProductDTO product);
	ResponseDTO reserveProduct(Long productId, Integer quantity);

}

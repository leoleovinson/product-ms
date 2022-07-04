package xyz.mynt.singson.productms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import xyz.mynt.singson.productms.DTO.ProductDTO;
import xyz.mynt.singson.productms.DTO.ProductsDTO;
import xyz.mynt.singson.productms.DTO.ResponseDTO;
import xyz.mynt.singson.productms.entity.CategoryEnum;
import xyz.mynt.singson.productms.entity.ProductEntity;
import xyz.mynt.singson.productms.exception.ProductmsException;
import xyz.mynt.singson.productms.mapper.ProductMapper;
import xyz.mynt.singson.productms.utilities.MapperUtils;
import xyz.mynt.singson.productms.utilities.RandomGeneratorUtils;

@Service
@EnableCaching
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public ProductsDTO getProducts() {
		
		List<ProductEntity> productsDB = productMapper.findAll();
		List<ProductDTO> products = new ArrayList<>();
		
		for (ProductEntity productsEntity: productsDB) {
			ProductDTO product = MapperUtils.toProductDTO(productsEntity);
			products.add(product);
		}
		System.out.println("returning from product service impl");
		return ProductsDTO.builder().products(products).build();
    }
	
	@Override
	@Cacheable(key = "#productId", value = "Product")
	public ProductDTO getProduct(Long productId) {
		validateProductId(productId);
		ProductEntity product = productMapper.findById(productId);
		System.out.println("accessed database");
		return MapperUtils.toProductDTO(product);
	}
	
	@Override
//	@CachePut(key = "#product.id", value = "Product")
	public ResponseDTO addProduct(ProductDTO product) {
		ProductEntity productEntity = MapperUtils.toProductEntity(product);
		productMapper.insertProduct(productEntity);
		System.out.println("accessed database");
		return ResponseDTO.builder().message("product added").build();
	}
	
	@Override
	public ResponseDTO reserveProduct(Long productId, Integer quantity) {
		validateProductId(productId);
		validateStock(productId, quantity);
		int newQuantity = updateQuantity(productId, quantity);
		productMapper.updateProductReservedQuantity(productId, newQuantity);
		return ResponseDTO.builder().message("product reserved").build();
	}
	
	private void validateProductId(Long productId) {
		ProductEntity product = productMapper.findById(productId);
		if (product==null) throw new ProductmsException("Product does not exist");
	}
	
	private void validateStock(Long productId, Integer quantity) {
		ProductEntity product = productMapper.findById(productId);
		int currentReservedQuantity = 0;
		if (product.getReservedQuantity()!=null) currentReservedQuantity = product.getReservedQuantity();
		if (product.getInStock() < (currentReservedQuantity + quantity)) {
			throw new ProductmsException("The product has not enough stock for the order");
		}
	}
	
	private int updateQuantity(Long productId, Integer quantity) {
		ProductEntity product = productMapper.findById(productId);
		int currentReservedQuantity = 0;
		if (product.getReservedQuantity()!=null) currentReservedQuantity = product.getReservedQuantity();
		return currentReservedQuantity + quantity;
	}

	@Override
	@CachePut(key = "#productId", value = "Product")
	public ProductDTO updateProduct(Long productId, ProductDTO product) {
		ProductEntity productEntity = MapperUtils.toProductEntity(product);
		productEntity.setId(productId);
		productMapper.update(productEntity);
		System.out.println("accessed database from update method");
		return MapperUtils.toProductDTO(productMapper.findById(productId));
	}


}

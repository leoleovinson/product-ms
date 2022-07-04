package xyz.mynt.singson.productms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.mynt.singson.productms.DTO.ProductDTO;
import xyz.mynt.singson.productms.DTO.ProductsDTO;
import xyz.mynt.singson.productms.DTO.RequestDTO;
import xyz.mynt.singson.productms.DTO.ResponseDTO;
import xyz.mynt.singson.productms.service.ProductLogService;
import xyz.mynt.singson.productms.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductLogService productLogService;
	
	@GetMapping
	public ResponseEntity<ProductsDTO> getProducts() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") Long productId){
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<ResponseDTO> addProduct(@Valid @RequestBody ProductDTO product){
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
		
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ResponseDTO> reserveProduct(@PathVariable("productId") Long productId, @RequestBody RequestDTO quantity){
		return new ResponseEntity<>(productService.reserveProduct(productId, quantity.getQuantity()), HttpStatus.OK);
	}

}

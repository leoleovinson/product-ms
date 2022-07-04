package xyz.mynt.singson.productms;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.mynt.singson.productms.DTO.ProductDTO;
import xyz.mynt.singson.productms.DTO.ResponseDTO;
import xyz.mynt.singson.productms.entity.CategoryEnum;
import xyz.mynt.singson.productms.entity.ProductEntity;
import xyz.mynt.singson.productms.mapper.ProductMapper;
import xyz.mynt.singson.productms.service.ProductService;
import xyz.mynt.singson.productms.utilities.MapperUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProductMsApplicationTests {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductMapper productMapper;

	List<ProductEntity> productsMock;

	@BeforeEach
	void setMockProducts() {

		ProductEntity apple = ProductEntity.builder().id(1L).name("apple").category(CategoryEnum.FRUITS_AND_VEGETABLES)
				.price(new BigDecimal(25)).inStock(100).available(true).build();
		ProductEntity milk = ProductEntity.builder().id(2L).name("milk").category(CategoryEnum.DAIRY_AND_CHILLED)
				.price(new BigDecimal(110)).inStock(30).available(true).build();

		productsMock = List.of(apple, milk);
	}

	@Test
	void getProductsTest() {
		when(productMapper.findAll()).thenReturn(productsMock);
		assertEquals(2, productService.getProducts().getProducts().size());
	}

	@Test
	void getProductTest() {
		// apple product
		when(productMapper.findById(1L)).thenReturn(productsMock.get(0));
		assertSame(ProductDTO.class, productService.getProduct(1L).getClass());

		// milk product
		when(productMapper.findById(2L)).thenReturn(productsMock.get(1));
		assertSame(ProductDTO.class, productService.getProduct(2L).getClass());

	}

	@Test
	void addProductTest() {
		ProductDTO appleDTO = ProductDTO.builder().id(1L).name("apple").category("FRUITS_AND_VEGETABLES")
				.price(new BigDecimal(25)).stock(100).available(true).build();
		ResponseDTO responseDto = ResponseDTO.builder().build();

		when(productMapper.insertProduct(MapperUtils.toProductEntity(appleDTO))).thenReturn(1);
		assertEquals(responseDto.getClass(), productService.addProduct(appleDTO).getClass());

	}

	@Test
	void reserveProductTest() {

		ResponseDTO responseDto = ResponseDTO.builder().build();

		when(productMapper.findById(1L)).thenReturn(productsMock.get(0));
		when(productMapper.updateProductReservedQuantity(1L, 20)).thenReturn(1);

		assertEquals(responseDto.getClass(), productService.reserveProduct(1L, 20).getClass());
	}

//	@Test
//	void getServiceProductsTest() {
//		when(productService.addProduct(appleDTO)).thenReturn(ResponseDTO.builder().build());
//		assertEquals(ResponseDTO.builder().build(), productService.addProduct(appleDTO));
//	}

}

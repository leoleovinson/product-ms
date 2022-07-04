package xyz.mynt.singson.productms;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mynt.singson.productms.DTO.ProductDTO;
import xyz.mynt.singson.productms.DTO.ProductsDTO;
import xyz.mynt.singson.productms.controller.ProductController;
import xyz.mynt.singson.productms.service.ProductService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
class ProductControllerTest {
	
	@Autowired
	ProductController productController;

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private ProductMsApplication productMsApplication;
	
	@MockBean
	private ProductService productService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(productMsApplication).build();
	}
	
	@Test
	void shouldCreateMockMvc() {
		assertNotNull(mockMvc);
	}
	
	@Test
	void getProductsTest() throws Exception {
		when(productService.getProducts()).thenReturn(ProductsDTO.builder().build());
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/products")
				)
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());	 
		
	}

}

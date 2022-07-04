package xyz.mynt.singson.productms.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO implements Serializable {
	
	private Long id;
	
	@NotNull(message = "Enter a product name")
	private String name;
	
	@NotNull(message = "Enter a product category")
	private String category;
	
	@Min(value = 1, message = "The value must be positive")
	@NotNull(message = "Enter a product price")
//	@Digits(fraction = 2, integer = 10, message = "Must only contain 2 decimal places")
	private BigDecimal price;
	
	@JsonProperty("in_stock")
	@Min(value = 1, message = "The value must be positive")
	@NotNull(message = "Enter product stock")
	private Integer stock;
	
	@JsonProperty("is_available")
	@NotNull(message = "Enter a product name")
	private Boolean available;

}

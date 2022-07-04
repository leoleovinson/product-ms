package xyz.mynt.singson.productms.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable {
	
	private Long id;
	
	private String name;
	
	private CategoryEnum category;
	
	private BigDecimal price;
	
	private Integer inStock;
	
	private Integer reservedQuantity;
	
	private Boolean available;

}

package xyz.mynt.singson.productms.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductsDTO {
	
	private List<ProductDTO> products;

}

package xyz.mynt.singson.productms.utilities;

import java.math.RoundingMode;

import xyz.mynt.singson.productms.DTO.ProductDTO;
import xyz.mynt.singson.productms.entity.CategoryEnum;
import xyz.mynt.singson.productms.entity.ProductEntity;
import xyz.mynt.singson.productms.exception.ProductmsException;

public class MapperUtils {
	
	public static ProductDTO toProductDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .category(productEntity.getCategory().toString())
                .price(productEntity.getPrice().setScale(2, RoundingMode.CEILING))
                .stock(productEntity.getInStock())
                .available(productEntity.getAvailable())
                .build();
    }
	
	public static ProductEntity toProductEntity(ProductDTO productDTO) {
        return ProductEntity.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .category(setCategory(productDTO.getCategory()))
                .price(productDTO.getPrice().setScale(2, RoundingMode.CEILING))
                .inStock(productDTO.getStock())
                .available(productDTO.getAvailable())
                .build();
    }
	
	private static CategoryEnum setCategory(String category) {
		CategoryEnum productCategory = null;
		for(CategoryEnum productType : CategoryEnum.values()) {
			if(productType.toString().equals(category)) {
				productCategory = productType;
			}
		}
		if(productCategory==null) {
			throw new ProductmsException("Invalid Product Category");
		}
		return productCategory;
	}

    private MapperUtils() {}

}

package xyz.mynt.singson.productms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import xyz.mynt.singson.productms.entity.ProductEntity;

@Component
@Mapper
public interface ProductMapper {
	
	@Select("SELECT * FROM products")
	List<ProductEntity> findAll();
	
	@Select("SELECT * FROM products WHERE id=#{productId}")
	ProductEntity findById(Long productId);
	
	@Insert("INSERT INTO products (id, name, category, price, inStock, available) "
			+ "values (#{id}, #{name}, #{category}, #{price}, #{inStock}, #{available})")
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
	int insertProduct(ProductEntity product);
	
	@Update("UPDATE products SET reservedQuantity=#{quantity} WHERE id=#{productId}")
	int updateProductReservedQuantity(Long productId, Integer quantity);
	
	@Update("UPDATE products SET name=#{name}, category=#{category}, price=#{price}, inStock=#{inStock} WHERE id=#{id}")
	int update(ProductEntity product);

}

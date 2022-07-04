package xyz.mynt.singson.productms;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.mynt.singson.productms.entity.ProductEntity;

@MappedTypes(ProductEntity.class)
@MapperScan("xyz.mynt.singson.productms.mapper")
@SpringBootApplication
public class ProductMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}

}

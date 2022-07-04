package xyz.mynt.singson.productms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBCreate {
	
	@Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    
    @Bean
    public void createDbTable() {
        try (
                Connection conn = DriverManager.getConnection(url,username,password);
                Statement stmt = conn.createStatement();
        ) {

            String productTable = "CREATE TABLE IF NOT EXISTS productmsdb.product ("
                    + "id VARCHAR(500) PRIMARY KEY NOT NULL,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "category ENUM('PANTRY_ESSENTIALS',"
                    + " 'DAIRY_AND_CHILLED',"
                    + " 'MEAT_POULTRY_AND_SEAFOOD',"
                    + " 'FRUITS_AND_VEGETABLES',"
                    + " 'SNACKS_AND_SWEETS',"
                    + " 'PERSONAL_CARE'),"
                    + "price DECIMAL,"
                    + "inStock INTEGER,"
                    + "reservedQuantity INTEGER,"
                    + "available BIT"
                    + ")";
            stmt.executeUpdate(productTable);
            
            String productsTable = "CREATE TABLE IF NOT EXISTS productmsdb.products ("
                    + "id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "category ENUM('PANTRY_ESSENTIALS',"
                    + " 'DAIRY_AND_CHILLED',"
                    + " 'MEAT_POULTRY_AND_SEAFOOD',"
                    + " 'FRUITS_AND_VEGETABLES',"
                    + " 'SNACKS_AND_SWEETS',"
                    + " 'PERSONAL_CARE'),"
                    + "price DECIMAL,"
                    + "inStock INTEGER,"
                    + "reservedQuantity INTEGER,"
                    + "available BIT"
                    + ")";
            stmt.executeUpdate(productsTable);

        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

}

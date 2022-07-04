package xyz.mynt.singson.productms.utilities;

import java.util.UUID;

import org.springframework.util.StringUtils;

public class RandomGeneratorUtils {
	
	public static String generateRandomUUID() {
		return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
	}
	
	private RandomGeneratorUtils() {}

}

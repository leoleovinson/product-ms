package xyz.mynt.singson.productms.DTO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ErrorDTO {
	
	private String title;
	private String details;
	private String errorType;
	private String errorCode;
	private String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z Z"));
	
	public ErrorDTO(String title, String details, String errorType, String errorCode) {
		this.title = title;
		this.details = details;
		this.errorType = errorType;
		this.errorCode = errorCode;
	}
	
	public ErrorDtoBuilder builder() {
		return new ErrorDtoBuilder();
	}

	public class ErrorDtoBuilder {
		private String title;
		private String details;
		private String errorType;
		private String errorCode;
		
		public ErrorDtoBuilder withTitle(String title){
			this.title = title;
			return this;
		}
		
		public ErrorDtoBuilder withDetails(String details){
			this.details = details;
			return this;
		}
		
		public ErrorDtoBuilder withErrorType(String errorType){
			this.errorType = errorType;
			return this;
		}
		
		public ErrorDtoBuilder withErrorCode(String errorCode){
			this.errorCode = errorCode;
			return this;
		}
		
		public ErrorDTO build() {
			return new ErrorDTO(title,details,errorType,errorCode);
		}
		
	}

}

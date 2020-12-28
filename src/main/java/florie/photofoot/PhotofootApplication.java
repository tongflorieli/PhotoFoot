package florie.photofoot;

import florie.photofoot.model.UserInfo;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(UserInfo.class)
@MapperScan("florie.photofoot.mapper")
@SpringBootApplication
public class PhotofootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotofootApplication.class, args);
	}

}

package in.sant.pdfgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
public class PdfgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfgatewayApplication.class, args);
	}

}

package in.sant.pdfgateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://localhost:8080/")
public interface PdfResource {
    @RequestMapping(method = RequestMethod.GET, value = "{fileName}")
    public byte[] download(@PathVariable(value = "fileName") String fileName);

}

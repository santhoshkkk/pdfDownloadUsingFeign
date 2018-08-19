package in.sant.pdfserver;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
public class DownloadController {

    @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = "application/pdf")
    public ResponseEntity<byte[]> download(@PathVariable(value = "name") String name) throws IOException {
        ClassPathResource resource = new ClassPathResource(name);

        InputStream inputStream = resource.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(name,name);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity responseEntity = new ResponseEntity(bytes,headers, HttpStatus.OK);

        return responseEntity;
    }
}

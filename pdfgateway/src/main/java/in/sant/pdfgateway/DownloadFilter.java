package in.sant.pdfgateway;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Slf4j
public class DownloadFilter extends ZuulFilter {

    @Autowired
    PdfResource pdfResource;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("download");
        StringBuffer requestURL = RequestContext.getCurrentContext().getRequest().getRequestURL();
        String fileName = requestURL.substring(requestURL.lastIndexOf("/")+1);
        byte[] download = pdfResource.download(fileName);
        File file = new File("copy" + fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(new String(download));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package com.codecool.virtualstylist.staticFiles;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping
public class StaticFilesController {

    @GetMapping(
            value = "/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable("fileName") String fileName) throws IOException {
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream(fileName);
        return IOUtils.toByteArray(in);
    }
}

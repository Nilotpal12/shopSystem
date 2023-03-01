package com.image.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import com.logger.Logging;
import com.qr.generator.QRGenerator;

@RestController
@RequestMapping("/api/image")
public class ImageController {
	
	private static final Logger logger = Logging.getInstance().getLogger();
	
	@GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getImage() {
        try {
        	//just a check
        	String data = "Service is UP";


            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) {
        try {
        	
        	QRGenerator.generate();
        	
            // read image file from local directory
            Path path = Paths.get("src/main/resources/images/" + imageName + ".png");
            byte[] data = Files.readAllBytes(path);

            // return image as a response
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(data);
        } catch (IOException e) {
        	
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.qr.generator;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRGenerator {

    public static void generate() {
    	// Basic data generation
        String qrCodeData = "https://google.com";
        String filePath = "src/main/resources/images/QRCode.png";
        int size = 125;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeData, size, fileType);
        System.out.println("QR Code generated successfully!");
        
    }

    private static void createQRImage(File qrFile, String qrCodeData, int size, String fileType) {
        try {
            // ByteMatrix for the QR-Code
            BitMatrix matrix = new QRCodeWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, size, size);
            // Path for your file
            Path path = FileSystems.getDefault().getPath(qrFile.getAbsolutePath());
            // QR code to the file
            MatrixToImageWriter.writeToPath(matrix, fileType, path);
        } catch (WriterException | IOException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        }
    }
    
    public static void move() {
        // Define the source and destination paths
        Path sourcePath = Paths.get("path/to/image.png");
        Path destinationPath = Paths.get("path/to/new/location/image.png");
        
        try {
            // Move the file from the source to the destination
            Files.move(sourcePath, destinationPath);
            System.out.println("Image moved successfully!");
        } catch (IOException e) {
            System.err.println("An error occurred while moving the image: " + e.getMessage());
        }
    }
	
}

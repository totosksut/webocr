package com.example.ocr.app.controller;

import org.bytedeco.*;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageWriteParam;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

@RestController
@RequestMapping("/fileocr")
public class FileCtrl {

	@PostMapping()
	public ResponseEntity<String> fileProcess(@RequestParam(name = "file", required = false) MultipartFile file)
			throws Exception {

		String result = "";
		
		try {
			PIX image = null;
			BytePointer outText = null;
			TessBaseAPI api = new TessBaseAPI();

			String path = null;

			if (System.getProperty("os.name").toLowerCase().equals("linux")) {

				InputStream input = new FileInputStream("/share/ocr/config.properties");

				Properties prop = new Properties();

				prop.load(input);

				path = prop.getProperty("path.tessdata");
			}

			if (api.Init(path, "Thai") != 0) {
				return ResponseEntity.ok("NOT FOUND");
			}


			if (file == null) {
				return ResponseEntity.ok("NOT FOUND FILE");
			}
			
			image = pixReadMem(file.getBytes(), file.getSize());
			api.SetImage(image);
			api.SetPageSegMode(1);
			// Get OCR result
			outText = api.GetUTF8Text();

			result = outText.getString();
			
			result = result.replace(" ", "");
			
			api.End();
			outText.deallocate();
			pixDestroy(image);
		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok(result);
	}
}

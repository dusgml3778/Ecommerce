package com.shopme.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	/**
	 * Save File
	 * @param uploadDir
	 * @param fileName
	 * @param multipartFile
	 * @throws IOException
	 */
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

		Path uploadPath = Paths.get(uploadDir);

		// Make dir
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);

		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			throw new IOException("Could Not Save File" + fileName, ex);
		}

	}

	/**
	 * Delete unused photos 
	 * @param dir
	 */
	public static void CleanDir(String dir) {
		Path dirPath = Paths.get(dir);

		try {
			Files.list(dirPath).forEach(file -> {
				if (!Files.isDirectory(file)) {
					try {
						Files.delete(file);
					} catch (IOException ex) {
						System.out.println("Could Not Delete File:" + file);
					}

				}
			});
		} catch (Exception e) {
			System.out.println("Could not lis directory" + dirPath);
		}
	}
}

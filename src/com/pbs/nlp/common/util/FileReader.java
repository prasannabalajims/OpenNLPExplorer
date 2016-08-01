package com.pbs.nlp.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileReader {

	/**
	 * Render file content as string by passing the file name with path as
	 * parameter
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileContentAsString(String filePath) {
		Objects.nonNull(filePath);
		try {
			// Using Java New IO for File and Path
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			System.out.println("IOException while reading the file");
			return null;
		}
	}
}

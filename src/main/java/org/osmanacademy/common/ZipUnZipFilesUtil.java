package org.osmanacademy.common;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZipUnZipFilesUtil {
	/**
	 * Use this method to Zip File
	 *
	 * @param srcFilePath  :- A String providing values for the absolute Source File
	 *                     Path to be zipped.
	 * @param destFilePath :- A String providing values for the absolute destination
	 *                     File Path where the zipped file shoould be stored.Just a
	 *                     fyi, this path should also contain the desired file name
	 *                     you want to name the file ending with .zip extention.
	 *
	 *
	 */
	public static ZipFile zipFile(String srcFilePath, String destFilePath) {
		File srcFilePathToBeZipped = new File(srcFilePath);
		File destFilePathOfZippedFile = new File(destFilePath);
		ZipFile zip = new ZipFile(destFilePathOfZippedFile);
		// Adding the list of files and directories to be zipped to a list
		List<File> fileList = new ArrayList<>();
		Arrays.stream(srcFilePathToBeZipped.listFiles()).forEach((File file) -> {
			fileList.add(file);
		});
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(CompressionMethod.DEFLATE);
		parameters.setCompressionLevel(CompressionLevel.FASTEST);
		parameters.setEncryptFiles(false);
		fileList.stream().forEach((File f) -> {
			try {
				if (f.isDirectory()) {
					zip.addFolder(f, parameters);
				} else {
					zip.addFile(f, parameters);
				}
			} catch (ZipException e) {
				e.printStackTrace();
			}

		});
		return zip;
	}

	private ZipUnZipFilesUtil() {

	}

}

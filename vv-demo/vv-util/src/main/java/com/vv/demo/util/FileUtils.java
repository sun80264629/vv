package com.vv.demo.util;

import java.io.File;

public class FileUtils {
	public static void main(String[] args) {
		String filePath = "H:\\项目";
		File root = new File(filePath);
		searchEmptyDir(root);
	}

	public static void searchEmptyDir(File root) {
		if(root.isFile()){
			return;
		}
		File[] dirFile = root.listFiles();
		String filename = "";
		for (File f : dirFile) {
			if ((f.listFiles() == null || f.listFiles().length == 0 ) && f.isDirectory()) {
				filename = f.getPath();
				System.out.println(filename + " is empty.");
				f.delete();
			} else {
				searchEmptyDir(f);
			}
		}
	}
}

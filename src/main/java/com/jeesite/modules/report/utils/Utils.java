package com.jeesite.modules.report.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	public static String uploadImg(String rootpath,MultipartFile image) {
		String filename=image.getOriginalFilename();
		FileOutputStream fs = null;
		try {
			File f=new File(rootpath);
			if(!f.exists()) {
				f.mkdirs();
			}
			System.out.println(rootpath);
			fs = new FileOutputStream(new File(rootpath + filename));
			fs.write(image.getBytes());
			fs.flush();
			return rootpath+filename;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

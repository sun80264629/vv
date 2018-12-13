package com.vv.demo.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestFileUtils {

	@Test
	public void testReadLinesFile() {
		try {
			File file = new File("E:/svn/00_项目文档/trunk/tunk-doc.txt");
			List<String> lines = FileUtils.readLines(file,"gb2312");
			// TRUNK svn://172.16.22.194/uuzz/LOTTERY/Develop/trunk/doc/
			// branches svn://172.16.22.194/uuzz/LOTTERY/Develop/branches/1.5.0/doc
			String svnUrl = "svn://172.16.22.194/uuzz/LOTTERY/Develop/branches/1.5.0/doc";
			for(String path : lines){
				System.out.println(svnUrl + path);
				FileUtils.writeStringToFile(new File("E:/svn/00_项目文档/trunk/150.txt"), svnUrl + path +"\n", true);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			fail("测试失败");
		}
		
		Assert.assertTrue(true);
	}
	


}

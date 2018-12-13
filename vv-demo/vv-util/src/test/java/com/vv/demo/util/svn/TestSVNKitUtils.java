/**
 * <br>项目名: vv-util
 * <br>文件名: TestSVNKitUtils.java
 * <br>Copyright 2015 北京壹平台科技有限公司
 */
package com.vv.demo.util.svn;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNAuthenticationException;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.replicator.SVNReplicationEditor;

/** 
 * <br>类 名: TestSVNKitUtils 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: 王巍 
 * <br>创 建： 2015年5月8日 
 * <br>版 本：v1.2 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class TestSVNKitUtils extends TestCase {
	@Test
	public void testConnect() throws Exception {
		File file = new File("E:/svn/00_项目文档/trunk/dir_list.txt");
		List<String> lines = FileUtils.readLines(file,"utf-8");
		String name = "wangwei";
		String password = "c48l3CMqkX#uCevM";
		String svnUrl = "svn://172.16.22.194/uuzz/LOTTERY/Develop/branches/1.5.0/doc/";
		for(String path : lines){
			try{
				SVNRepository repository = SVNKitUtils.connect(svnUrl + path, name, password);
				repository.checkPath("", -1);
				//String subPath = "E:" + repository.getLocation().getPath();
				//System.out.println(subPath + " - 读");
				//SVNReplicationEditor editor = new SVNReplicationEditor(repository, commitEditor, revision)
				//repository.checkout(-1, subPath, true, repository.getCommitEditor(logMessage, mediator));
				SVNKitUtils.listEntries(repository, "");
				System.out.println(path + " - 读");
			}catch(SVNAuthenticationException e){
				System.out.println(path + " - 不可读");
			}catch(SVNException e){
				System.out.println(path + " - 不可读");
			}
		}
		
	}

}

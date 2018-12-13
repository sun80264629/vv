package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNClientUtils {
	private static SVNClientManager ourClientManager = null;

	public static void createClientManager(String username, String password) throws SVNException {
		DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
		ourClientManager = SVNClientManager.newInstance(options, username, password);
	}

	/**
	 * <br>
	 * 描 述：svn检出 <br>
	 * 作 者：Vv <br>
	 * 历 史: (版本) 作者 时间 注释
	 * 
	 * @param url
	 * @param revision
	 * @param destPath
	 * @param isRecursive
	 * @return 
	 * @throws SVNException
	 */
	@SuppressWarnings("deprecation")
	public static long checkout(String url, SVNRevision revision,
			File destPath, boolean isRecursive) throws SVNException {
		SVNURL repositoryURL = SVNURL.parseURIEncoded(url);
		SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
		/*
		 * sets externals not to be ignored during the checkout
		 */
		updateClient.setIgnoreExternals(false);
		/*
		 * returns the number of the revision at which the working copy is
		 */
		return updateClient.doCheckout(repositoryURL, destPath, revision, revision,
				isRecursive, false);// .doCheckout(url, destPath, revision,
									// revision, isRecursive);
	}

	public static void main(String[] args) {
		String urlRoot = "svn://172.16.22.194/uuzz/LOTTERY/Develop/branches/1.5.0/doc/";
		String destRoot = "E:\\svn\\00_项目文档\\branches_1.5.0\\";
		
		// accessing svn
		String name = "wangwei";
		String password = "c48l3CMqkX#uCevM";
		try {
			createClientManager(name,password);
		} catch (SVNException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		String svnFile = "doc/svn/svn_config.txt";
		List<String> dirs = null;;
		try {
			dirs = FileUtils.readLines(new File(svnFile));
			for(String dir : dirs){
				try {
					String url = urlRoot + dir ;
					String destDir = destRoot + dir;
					File wcDir = new File(destRoot+dir);
					if (wcDir.exists()) {
						System.out.println("the destination directory '" + wcDir.getAbsolutePath() + "' already exists!");
					} else {
						wcDir.mkdirs();
					}
					System.out.println("Checking out a working copy from '" + url + "'...");
					
					/*
					 * recursively checks out a working copy from url into wcDir.
					 * SVNRevision.HEAD means the latest revision to be checked out.
					 */
					long revision = checkout(url, SVNRevision.HEAD, wcDir, true);
					
					System.out.println("Checking out a working copy from '" + url + "'  success!  revision : " + revision);
					
				} catch (SVNException e) {
					System.out.println("======================= Error ======================= " + e.getMessage());
					// TODO 自动生成的 catch 块
					//e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			System.out.println("error read file with " + svnFile + "...");
			e1.printStackTrace();
		}

		System.out.println("success!");
	}
}
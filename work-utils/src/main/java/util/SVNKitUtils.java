package util;

import java.util.Collection;
import java.util.Iterator;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNKitUtils {
	/**
	 * <br>描 述：与svn版本库建立连接
	 * <br>作 者：Vv
	 * @param svnUrl 版本库SVN URL
	 * @param name	用户名
	 * @param password 密码
	 * @return SVNRepository
	 */
	public static SVNRepository connect(String svnUrl, String name,String password){
		//定义svn版本库的URL。
        SVNURL repositoryURL = null;
        // 定义版本库
        SVNRepository repository = null;
		try {
			//获取SVN的URL。
        	repositoryURL = SVNURL.parseURIEncoded(svnUrl);
			// 建立连接
			repository = SVNRepositoryFactory.create(repositoryURL);
			
			 /*
	         * 对版本库设置认证信息。
	         */
	        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
	        repository.setAuthenticationManager(authManager);
		} catch (SVNException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return repository;
	}
	
	/**
	 * <br>描 述：此函数递归的获取版本库中某一目录下的所有条目。
	 * <br>作 者：Vv
	 * @param repository SVNRepository
	 * @param path	当前路径，""为根路径
	 * @throws SVNException
	 */
	public static void listEntries(SVNRepository repository, String path) throws SVNException {
		// 获取版本库的path目录下的所有条目。参数－1表示是最新版本。
		Collection entries = repository.getDir(path, -1, null, (Collection) null);
		Iterator iterator = entries.iterator();
		while (iterator.hasNext()) {
			SVNDirEntry entry = (SVNDirEntry) iterator.next();
			if (!SVNNodeKind.DIR.equals(entry.getKind())) {
				System.out.println("/" + (path.equals("") ? "" : path + "/") + entry.getName());
			}
			/*
			 * 检查此条目是否为目录，如果为目录递归执行
			 */
			if (entry.getKind() == SVNNodeKind.DIR) {
				listEntries(repository, (path.equals("")) ? entry.getName() : path + "/" + entry.getName());
			}
		}
	}
	
	public static void main(String[] args) {
		String svnUrl = "svn://172.16.22.194/uuzz/LOTTERY/Develop/branches/1.5.0/doc/04-设计文档/03详细设计";
		String name = "wangwei";
		String password = "c48l3CMqkX#uCevM";
		try {
			SVNRepository repository = SVNKitUtils.connect(svnUrl, name, password);
			SVNNodeKind svnnode = repository.checkPath("", -1);
			//SVNKitUtils.listEntries(repository, "");
			System.out.println();
		} catch (SVNException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}

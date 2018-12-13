package util;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import entity.SessionGroup;

/**
 * <br>类 名: SSHSessionByMobaXterm 
 * <br>描 述: 根据各个环境生成ssh连接地址（MobaXterm）
 * <br>作 者: 王巍 
 * <br>创 建： 2015年7月1日 
 * <br>版 本：v1.2 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class SSHSessionBySSH {
	private static MessageFormat xftpTemplate = null;
	private static MessageFormat xsshTemplate = null;
	
	public static MessageFormat getXftpTemplate() throws IOException{
		if(xftpTemplate == null){
			 String content =  FileUtils.readFileToString(new File("doc/template/template_xftp.xfp"));
			 xftpTemplate = new MessageFormat(content);
		}
		return xftpTemplate;
	}
	
	public static MessageFormat getXsshTemplate() throws IOException{
		if(xsshTemplate == null){
			 String content =  FileUtils.readFileToString(new File("doc/template/template_xssh.xsh"));
			 xsshTemplate = new MessageFormat(content);
		}
		return xsshTemplate;
	}
	
	public static List<String> generateXftp(SessionGroup group){
		List<String> SessionConfig = null;
		SessionConfig = new ArrayList<String>();
		
		try {
			String filename = null;
			for(String s : group.getIpList()){
				String[] params = {s, group.getUsername(), group.getPassword()};
				String content = getXftpTemplate().format(params);
				
				filename = "doc/XFTP/"+ group.getEnvironmentName() + "/" + s + ".xfp";
				System.out.println(filename);
				FileUtils.writeStringToFile(new File(filename), content);
			}
		
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return SessionConfig;
	}
	
	public static List<String> generateXssh(SessionGroup group){
		List<String> SessionConfig = null;
		SessionConfig = new ArrayList<String>();
		
		try {
			String filename = null;
			for(String s : group.getIpList()){
				String[] params = {s, group.getUsername(), group.getPassword()};
				String content = getXsshTemplate().format(params);
				
				filename = "doc/SSH/"+ group.getEnvironmentName() + "/" + s + ".xsh";
				System.out.println(filename);
				FileUtils.writeStringToFile(new File(filename), content);
			}
		
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return SessionConfig;
	}
	
	public static void main(String[] args) {
		// 环境名称
		String environmentName = "";
		   
		List<SessionGroup> groupList = new ArrayList<SessionGroup>();
		
		SessionGroup group = null;
		
		/*group = new SessionGroup("151Tianyi", "doc/TIANYI.txt", "admin", "ef6WmQo=");
		groupList.add(group);
		
		group = new SessionGroup("山东审核", "doc/山东审核环境.txt", "admin", "ef6WmQo=");
		groupList.add(group);
		
		group = new SessionGroup("海南审核", "doc/海南审核环境.txt", "admin", "ef6WmQo=");
		groupList.add(group);
		
		group = new SessionGroup("M6性能", "doc/M6性能测试.txt", "admin", "ef6WmQo=");
		groupList.add(group);
		
		group = new SessionGroup("23X环境", "doc/23X环境.txt", "admin", "ef6WmQo=");
		groupList.add(group);
		
		group = new SessionGroup("2X环境", "doc/2X环境.txt", "admin", "ef6WmQo=");
		groupList.add(group);
		
		group = new SessionGroup("19X环境", "doc/19X环境.txt", "admin", "ef6WmQo=");
		groupList.add(group);*/
		
		group = new SessionGroup("国电环境", "doc/server/国电.txt", "root", "+4qATuXpmgM=");
		groupList.add(group);
		
		for(SessionGroup g : groupList) {
			generateXftp(g);
			
			g.setPassword("SNqIgxNJ68o=");
			generateXssh(g);
		}
		
		
	}
}

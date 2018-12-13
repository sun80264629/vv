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
 * <br>描 述: 根据各个环境生成ssh连接地址（MobaXterm v7.7）
 * <br>作 者: 王巍 
 * <br>创 建： 2015年7月1日 
 * <br>版 本：v1.2 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class SSHSessionByMobaXterm {
	/**
	 * SFTP 配置模板
	 */
	private static final MessageFormat FORMAT_SFTP = new MessageFormat("{0} ({1}) [SFTP]=#140#7%{2}%22%{3}%-1%0%%0%0%%0%%1080%%%#MobaFont%10%0%0%0%15%236,236,236%0,0,0%180,180,192%0%-1%0%%xterm%-1%0%0,0,0%54,54,54%255,96,96%255,128,128%96,255,96%128,255,128%255,255,54%255,255,128%96,96,255%128,128,255%255,54,255%255,128,255%54,255,255%128,255,255%236,236,236%255,255,255#0");
	/**
	 * SSH 配置模板
	 */
	private static final MessageFormat FORMAT_SSH = new MessageFormat("{0} ({1}) [SSH]=#109#0%{2}%22%{3}%%-1%-1%%%22%%0%0%Interactive shell%%%-1%0%0%0%%1080#MobaFont%10%0%0%0%15%236,236,236%0,0,0%180,180,192%0%-1%0%%xterm%-1%0%0,0,0%54,54,54%255,96,96%255,128,128%96,255,96%128,255,128%255,255,54%255,255,128%96,96,255%128,128,255%255,54,255%255,128,255%54,255,255%128,255,255%236,236,236%255,255,255#0");
	
	/**
	 * 文件夹标签模板
	 */
	private static final MessageFormat FORMAT_SESSION = new MessageFormat("\r\n[Bookmarks_{0}]\r\nSubRep={1}\r\nImgNum=41");
	
	/**
	 * <br>描 述：生成SFTP配置
	 * <br>作 者：王巍 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param ip	连接地址	
	 * @param username	用户名
	 * @return 配置信息
	 */
	public static String generateSFTP(String ip, String username){
		String[] params = {ip,username,ip,username};
		return FORMAT_SFTP.format(params);
	}
	
	/**
	 * <br>描 述：生成SSH配置
	 * <br>作 者：王巍 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param ip	连接地址	
	 * @param username	用户名
	 * @return 配置信息
	 */
	public static String generateSSH(String ip, String username){
		String[] params = {ip,username,ip,username};
		return FORMAT_SSH.format(params);
	}
	
	/**
	 * <br>描 述：根据环境批量生成配置信息
	 * <br>作 者：王巍 
	 * <br>历 史: (版本) 作者 时间 注释
	 * @param group	 分组信息
	 * @param username	用户名
	 * @return 配置信息集合
	 */
	public static List<String> generateGroup(SessionGroup group, String username){
		List<String> SessionConfig = null;
		SessionConfig = new ArrayList<String>();
		SessionConfig.add(FORMAT_SESSION.format(new String[]{group.getNo(), group.getEnvironmentName()}));
		
		for(String s : group.getIpList()){
			System.out.println(s);
			SessionConfig.add(generateSSH(s,username));
			SessionConfig.add(generateSFTP(s,username));
		}
		return SessionConfig;
	}
	
	public static void main(String[] args) {
		// 环境名称
		String environmentName = "";
		   
		List<String> config = new ArrayList<String>();
		try {
			String begin = "[Bookmarks]\r\nSubRep=\r\nImgNum=41\r\n";
			config.add(begin);
			
			List<SessionGroup> groupList = new ArrayList<SessionGroup>();
			
			SessionGroup group = null;
			
			/* group = new SessionGroup("151Tianyi", "doc/TIANYI.txt");
			groupList.add(group);*/
			
			/*group = new SessionGroup("山东审核", "doc/山东审核环境.txt");
			groupList.add(group);*/
			
			 group = new SessionGroup("海南审核", "doc/海南审核环境.txt");
			groupList.add(group);
			
			/* group = new SessionGroup("M6性能", "doc/M6性能测试.txt");
			groupList.add(group);*/
			
			/* group = new SessionGroup("23X环境", "doc/23X环境.txt");
			groupList.add(group);*/
			
			group = new SessionGroup("2X环境", "doc/2X环境.txt");
			groupList.add(group);
			
			for(SessionGroup g : groupList) {
				List<String> SessionConfig = generateGroup(g, "admin");
				config.addAll(SessionConfig);
			}
			
			FileUtils.writeLines(new File("doc/MobaXterm Sessions.mxtsessions"),"gbk", config);
		
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	

}

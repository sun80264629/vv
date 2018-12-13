package entity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class SessionGroup {
	String environmentName;
	static int num = 1;
	String no;
	List<String> ipList;
	String username="";
	String password="";
	
	/**
	 * @param environmentName 
	 * @param filename
	 */
	public SessionGroup(String environmentName, String filename){
		this.environmentName = environmentName;
		no = String.valueOf(num ++);
		try {
			ipList = FileUtils.readLines(new File(filename));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * @param environmentName 
	 * @param filename
	 * @param username
	 * @param password
	 */
	public SessionGroup(String environmentName, String filename, String username, String password){
		this(environmentName, filename);
		this.username = username;
		this.password = password;
	}

	public String getEnvironmentName() {
		return environmentName;
	}

	public String getNo() {
		return String.valueOf(no);
	}

	public List<String> getIpList() {
		return ipList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

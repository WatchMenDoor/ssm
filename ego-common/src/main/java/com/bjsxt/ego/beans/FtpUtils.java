package com.bjsxt.ego.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpUtils {
	public static void main(String[] args) {
		FTPClient client = new FTPClient();
		String hostname ="192.168.9.141";
		int port = 21;
		String username = "ftpuser";
		String password = "ftpuser";
		
		String pathname = "/home/ftp/user";
		
		String remote = "demo.jpg";
		InputStream local = null;
		//uploadFile(client, hostname, port, username, password, pathname, remote);
	}

	public static boolean uploadFile(String hostname, 
			int port, String username, String password,
			String pathname,String remote,InputStream local) {
		FTPClient client = new FTPClient();
		
		boolean flag = false;
		try {
			client.connect(hostname, port);
			client.login(username, password);
			
			//设置上传文件的类型
			client.setFileType(FTP.BINARY_FILE_TYPE);
			
			//切换工作目录
			if (!client.changeWorkingDirectory(pathname)) {
				if (client.makeDirectory(pathname)) {
					client.changeWorkingDirectory(pathname);
					
				}
				
			}
			
			local = new FileInputStream("D:/pic/1.jpg");
			client.storeFile(remote, local);
			
			local.close();
			client.logout();
			client.disconnect();
			flag = true;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

}

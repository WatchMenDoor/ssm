package com.bjsxt.ego.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new
				ClassPathXmlApplicationContext("spring/applicationContext-dao.xml",
						"spring/applicationContext-service.xml",
						"spring/applicationContext-tx.xml",
						"spring/applicationContext-dubbo.xml");
		
		ac.start();
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ac.stop();
	}

}

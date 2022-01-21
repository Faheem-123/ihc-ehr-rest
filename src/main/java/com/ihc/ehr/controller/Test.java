package com.ihc.ehr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/test")
public class Test {


	@Value("${ihc.common.api.endpiont}")
	private String ihcCommonApiEndpiont;
	
	@RequestMapping("/hello")
	public String sayHello() {
		
		System.out.println("hello");
		System.out.println("ihcCommonApiEndpiont:"+ihcCommonApiEndpiont);
		
		//QRDA_Import q=new QRDA_Import();
		//q.ProcessFile();
		
		return "Hello";
	}
		
}

package com.cn.naive.naiveseg.client;

import java.util.List;

import com.cn.naive.naiveseg.core.NaiveSeg;

public class Test {
	public static void main(String[] args) {
		String str = "创新工场董事长李开复曾于1998年到2005年供职于Microsoft公司，与Bill.Gates等公司高管都有过深入交流，曾参与开发过Vista。2005年7月加入Google公司。";

		NaiveSeg naiveSeg=new NaiveSeg();
		List<String> list = naiveSeg.analyze(str);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
			System.out.print("/");
		}
	}
}

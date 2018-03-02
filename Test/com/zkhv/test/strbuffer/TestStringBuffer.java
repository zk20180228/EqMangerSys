package com.zkhv.test.strbuffer;

public class TestStringBuffer {
	
	//测试刚建立StringBuffer的长度，与其容量两码子
	public static void main(String[] args) {
		StringBuffer stringBuffer = new StringBuffer();
		System.out.println(stringBuffer.length());//0
		System.out.println(stringBuffer.toString().length());//0
	}
	

}

package com.dh.ora.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MetaFilAssiant {
	
	public static void checkAndCreate(String dirname) throws Exception{
		File file = new File(dirname);
		if(!file.exists()){
			file.mkdirs();	
		}
	}
	
	public static List<String> fileListWithFullName(String dirname) throws Exception{
		File dir = new File(dirname);
		File[] fs = dir.listFiles();
		List<String> list  = new ArrayList<String>();
		for(int i =0;i<fs.length;i++){
			if(!fs[i].isDirectory()){
				list.add(fs[i].getAbsolutePath());
			}
			 if(fs[i].isDirectory()){
				 fileListWithFullName(fs[i].getAbsolutePath());
			 }
		}
		return list;
	}
	
	public static List<String> fileList(String dirname,String webpath) throws Exception{
		File dir = new File(dirname);
		File[] fs = dir.listFiles();
		List<String> list  = new ArrayList<String>();
		String tmpFileName ="";
		char oldchar = '\\';
	    char newchar = '/';
		for(int i =0;i<fs.length;i++){
			if(!fs[i].isDirectory()){
				tmpFileName = fs[i].getAbsolutePath();
				tmpFileName = tmpFileName.substring(webpath.length(), tmpFileName.length());
				tmpFileName = tmpFileName.replace(oldchar,newchar);
				list.add(tmpFileName);
			}
			if(fs[i].isDirectory()){
				 fileList(fs[i].getAbsolutePath(),webpath);
			}
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception{
		fileList("D:\\Project\\git\\dhweb\\dhweb\\WebContent\\img\\postImg\\10016","D:\\Project\\git\\dhweb\\dhweb\\WebContent");
	}
}

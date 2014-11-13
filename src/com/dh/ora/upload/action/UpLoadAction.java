package com.dh.ora.upload.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.dh.ora.s001.bean.User;
import com.dh.ora.tool.MetaFilAssiant;
import com.x.orange.Action;

public class UpLoadAction extends Action {
	
	private static final long serialVersionUID = 6728180039514530051L;

	private String uploadContentType;
	private String uploadFileName;
	private String CKEditorFuncNum;
	private String CKEditor;
	private String langCode;
	private File upload;
	
	public String getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}


	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}


	public String getCKEditor() {
		return CKEditor;
	}


	public void setCKEditor(String cKEditor) {
		CKEditor = cKEditor;
	}


	public String getLangCode() {
		return langCode;
	}


	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String upload() throws Exception{
		User usr = (User) getSessionUser();
		this.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// CKEditor提交的很重要的一个参数
		String callback = getRequest().getParameter("CKEditorFuncNum"); 
		String expandedName = "";  //文件扩展名
		if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")|| uploadContentType.equals("image/jpg")) {
			//IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		}else if(uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")){
			//IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		}else if(uploadContentType.equals("image/gif")){
			expandedName = ".gif";
		}else if(uploadContentType.equals("image/bmp")){
			expandedName = ".bmp";
		}else{
			out.println("<script type=\"text/javascript\">");  
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');"); 
			out.println("</script>");
			return null;
		}
		
		if(upload.length() > 600*1024){
			out.println("<script type=\"text/javascript\">");  
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于600k');"); 
			out.println("</script>");
			return null;
		}		
		InputStream is = new FileInputStream(upload);
		String uploadPath = ServletActionContext.getServletContext().getRealPath("/img/postImg/"+usr.getUserId());
		MetaFilAssiant.checkAndCreate(uploadPath);
		String fileName = java.util.UUID.randomUUID().toString();  //采用时间+UUID的方式随即命名
		fileName += expandedName;
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);   
        byte[] buffer = new byte[1024];   
        int length = 0;
        while ((length = is.read(buffer)) > 0) {   
            os.write(buffer, 0, length);   
        }   
        is.close();
        os.close();
		
        // 返回“图像”选项卡并显示图片
		out.println("<script type=\"text/javascript\">");  
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "http://127.0.0.1:8080/img/postImg/"+usr.getUserId()+"/" + fileName + "','')");  
		out.println("</script>");
		return null;//"msg_info";
	}
	
	public String load_page() throws Exception{
		return "load_page";
	}
	public String load_single_pic() throws Exception{
		User usr = (User) getSessionUser();
		this.getResponse().setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String picid = getRequest().getParameter("picid");
		String expandedName = "";  //文件扩展名
		if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")|| uploadContentType.equals("image/jpg")) {
			//IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		}else if(uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")){
			//IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		}else if(uploadContentType.equals("image/gif")){
			expandedName = ".gif";
		}else if(uploadContentType.equals("image/bmp")){
			expandedName = ".bmp";
		}else{
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('"+"文件格式不正确,必须为.jpg/.gif/.bmp/.png文件"+"');");
			out.println("window.opener=null;window.close();");
			out.println("</script>");
			return null;
		}		
		if(upload.length() > 600*1024){
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('"+"文件大小不得大于600k"+"');");
			out.println("window.opener=null;window.close();");
			out.println("</script>");
			return null;
		}	
		InputStream is = new FileInputStream(upload);
		String uploadPath = ServletActionContext.getServletContext().getRealPath("/img/postImg/"+usr.getUserId());
		MetaFilAssiant.checkAndCreate(uploadPath);
		String fileName = java.util.UUID.randomUUID().toString();  //采用时间+UUID的方式随即命名
		fileName += expandedName;
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);   
        byte[] buffer = new byte[1024];   
        int length = 0;
        while ((length = is.read(buffer)) > 0) {   
            os.write(buffer, 0, length);   
        }   
        is.close();
        os.close();
        String url = "http://127.0.0.1:8080/img/postImg/" + usr.getUserId()+"/"+fileName; 
		out.println("<script type=\"text/javascript\">");
		out.println("window.opener.document.getElementById('single_pic"+picid+"').innerHTML="+"\"<img src=\'"+url+"\'"+"  width=80px height=80px />\";");
		out.println("window.opener.document.getElementById('single_pic_url_"+picid+"').value=\""+url+"\";");
		out.println("window.opener=null;window.close();");
		out.println("</script>");
		return null;//"msg_info";
	}
}
	


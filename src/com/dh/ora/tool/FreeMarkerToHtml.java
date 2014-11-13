package com.dh.ora.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerToHtml {
	
	static Logger logger = Logger.getLogger(FreeMarkerToHtml.class);
    
	private Configuration freemarker_cfg = null; 
	
	private String template_path = "";
	
	public String getTemplate_path() {
		return template_path;
	}

	public void setTemplate_path(String template_path) {
		this.template_path = template_path;
	}

	/** 
     * 获取freemarker的配置. freemarker本身支持classpath,目录和从ServletContext获取. 
     */ 
    protected Configuration getFreeMarkerCFG() 
    { 
        if (null == freemarker_cfg) 
        {
            freemarker_cfg = new Configuration();
            
            freemarker_cfg.setDefaultEncoding("UTF-8");
            //htmlskin是放在classpath下的一个目录 
            //freemarker_cfg.setClassForTemplateLoading(this.getClass(), "/");
            //freemarker_cfg.setDirectoryForTemplateLoading(arg0);
            //freemarker_cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
            FileTemplateLoader loader = null;
			try {
				loader = new FileTemplateLoader(new File(getTemplate_path()));
			} catch (IOException e) {
				e.printStackTrace();
			}             
            freemarker_cfg.setTemplateLoader(loader);
        } 
        
        return freemarker_cfg; 
    }
    
    /** 
     * 生成静态文件. 
     * 
     * @param templateFileName 模板文件名,相对htmlskin路径,例如"/tpxw/view.ftl" 
     * @param propMap 用于处理模板的属性Object映射 
     * @param htmlFilePath 要生成的静态文件的路径,相对设置中的根路径,例如 "/tpxw/1/2005/4/" 
     * @param htmlFileName 要生成的文件名,例如 "1.htm" 
     */ 
    public boolean geneHtmlFile(String templateFileName,Map propMap, String htmlFilePath,String htmlFileName ) 
    { 
         //@todo 从配置中取得要静态文件存放的根路径:需要改为自己的属性类调用 
        //String sRootDir = "";
      
        try 
        { 
            Template t = getFreeMarkerCFG().getTemplate(templateFileName);
            
            t.setEncoding("UTF-8");
            //如果根路径存在,则递归创建子目录 
            creatDirs(htmlFilePath); 
          
            File htmlFile = new File(htmlFilePath + "/" + htmlFileName);

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile),"UTF-8"));
                        
            t.process(propMap, out); 
             
            out.flush();
            
        } 
        catch (TemplateException e) 
        { 
            logger.error("Error while processing FreeMarker template " + templateFileName,e); 
            return false; 
        } 
        catch (IOException e) 
        { 
            logger.error("Error while generate Static Html File " + htmlFileName,e); 
            return false; 
        } 

        return true; 
    } 
    /** 
     * 创建多级目录 
     * 
     * @param aParentDir String 
     * @param aSubDir    以 / 开头 
     * @return boolean 是否成功 
     */ 
    public void creatDirs(String htmlFilePath) 
    {   	
        File file = new File(htmlFilePath);
        file.mkdirs();
    }    

}

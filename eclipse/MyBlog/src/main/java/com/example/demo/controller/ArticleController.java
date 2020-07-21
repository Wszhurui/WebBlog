package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService service;
	
	@RequestMapping("/getAllArticle")
	@ResponseBody
	public List<Article> getAllArticle() throws Exception
	{
		return service.getAllArticle();
	}
	
	
	@RequestMapping("/admin/editArticle")
	@ResponseBody
	public boolean editArticle(
			@RequestParam(name="title")String title,
			@RequestParam(name="content")String content,
			@RequestParam(name="id")int id
			) throws Exception
	{
		return service.editArticle(title, content, id);
	}
	
	
	@RequestMapping("/admin/addArticle")
	@ResponseBody
	public boolean addArticle(
			@RequestParam(name="title")String title,
			@RequestParam(name="content")String content
			)throws Exception
	{
		return service.addArticle(title, content);
	}
	
	
	@RequestMapping("/admin/deleteArticle")
	@ResponseBody
	public boolean deleteArticle(@RequestParam(name="id")int id)throws Exception
	{
		return service.deleteArticle(id);
	}
	
	@RequestMapping("/formFile")
	@ResponseBody
	public String uploadImg(@RequestParam("file") MultipartFile file) throws Exception
	{
		//表单图片文件上传
		String dir=ResourceUtils.getURL("classpath:").getPath()+"static/uploadImg/";
		File f=new File(dir);
		if(!f.exists())
		{
			f.mkdirs();
		}
		if(file.getSize()>1024*1024*5)
		{
			return "文件过大";
		}
		String fileName=file.getOriginalFilename();
		String fileExt=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        file.transferTo(new File(dir+newFileName));
        return "/api/uploadImg/"+newFileName;
	}
	
	@RequestMapping("/admin/uploadImg")
	@ResponseBody
	public String uploadImg(HttpServletRequest request) throws IOException
	{
        MultipartFile mFile =null;
        File fpath=new File(ResourceUtils.getURL("classpath:").getPath());
        if(!fpath.exists()) {
        	fpath=new File("");
        }
        File upload=new File(fpath.getAbsolutePath(),"static/upload/");
        if(!upload.exists())
        {
        	upload.mkdirs();
        }
		JSONObject obj=new JSONObject();
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        mFile=mRequest.getFile("imgFile");
        if(mFile==null){
        	obj.put("error",1);
        	obj.put("message:","上传图片为空");
        	return obj.toJSONString();
        }
        if (mFile.getSize() > 1024*1024*5) {
        	obj.put("error",1);
        	obj.put("message:","图片大小超过限制");
        	return obj.toJSONString();
        	}  
        String fileName = mFile.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        mFile.transferTo(new File(fpath.getAbsolutePath(),"static/upload/"+newName));
        obj.put("error",0);
        obj.put("url","/upload/"+newName);
		return obj.toJSONString();
	}

}

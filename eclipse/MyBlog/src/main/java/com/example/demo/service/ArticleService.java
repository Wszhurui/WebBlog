package com.example.demo.service;

import java.util.List;


import com.example.demo.entity.Article;

public interface ArticleService {

	public boolean addArticle(String title,String content)throws Exception;
	
	public List<Article> getAllArticle()throws Exception;
	
	public boolean editArticle(String title,String content,int id)throws Exception;
	
	public boolean deleteArticle(int id)throws Exception;
}

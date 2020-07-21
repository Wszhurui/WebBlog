package com.example.demo.serviceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleMapper mapper;

	@Override
	public boolean addArticle(String title, String content) throws Exception {
		
		int success=mapper.addArticle(title, content);
		// TODO Auto-generated method stub
		if(success==1)
			return true;
		else
			return false;
	}

	@Override
	public List<Article> getAllArticle() throws Exception {
		// TODO Auto-generated method stub
		return mapper.getAllArticle();
	}

	@Override
	public boolean editArticle(String title, String content, int id) throws Exception {
		// TODO Auto-generated method stub
		int success=mapper.editArticle(title, content, id);
		if(success==1)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteArticle(int id) throws Exception {
		// TODO Auto-generated method stub
		int success=mapper.deleteArticle(id);
		if(success==1)
			return true;
		else
			return false;
	}
}

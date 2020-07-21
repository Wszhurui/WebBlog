package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Article;

@Mapper
public interface ArticleMapper {
	
	@Select("select * from t_article")
	public List<Article> getAllArticle()throws Exception;
	
	@Insert("insert into t_article(title,content)values(#{title},#{content})")
	public int addArticle(String title,String content)throws Exception;
	
	@Update("update t_article set title=#{title},content=#{content} where id=#{id}")
	public int editArticle(String title,String content,int id)throws Exception;
	
	@Delete("delete from t_article where id=#{id}")
	public int deleteArticle(int id)throws Exception;

}

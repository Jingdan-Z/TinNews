package com.laioffer.tinnews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.laioffer.tinnews.model.Article;

import java.util.List;

@Dao
public interface NewsDao {
    //data access object
    //the apis needed to access article table
    //at compile time, Room will implements this class
    @Insert
    void saveArticles(Article article);
    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();
    @Delete
    void deleteArticle(Article article);

}

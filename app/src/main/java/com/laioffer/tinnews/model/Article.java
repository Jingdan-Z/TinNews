package com.laioffer.tinnews.model;

import java.util.Objects;

public class Article {
    //what we need to displace from the NewsApi response
    //source, id etc are omitted
    public String author;
    public String title;
    public String content;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(author, article.author) && Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(description, article.description) && Objects.equals(url, article.url) && Objects.equals(urlToImage, article.urlToImage) && Objects.equals(publishedAt, article.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, content, description, url, urlToImage, publishedAt);
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}

package com.hsl.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "commentAbc")
public class CommentRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rate;
    private String author;
    private String comment;
    private int likeComment;

    public CommentRate() {
    }

    public CommentRate(int rate, String author, String comment, int like) {
        this.rate = rate;
        this.author = author;
        this.comment = comment;
        this.likeComment = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikeComment() {
        return likeComment;
    }

    public void setLikeComment(int likeComment) {
        this.likeComment = likeComment;
    }
}

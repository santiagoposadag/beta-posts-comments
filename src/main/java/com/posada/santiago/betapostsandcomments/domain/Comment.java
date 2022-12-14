package com.posada.santiago.betapostsandcomments.domain;


import co.com.sofka.domain.generic.Entity;
import com.posada.santiago.betapostsandcomments.domain.values.Author;
import com.posada.santiago.betapostsandcomments.domain.values.CommentId;
import com.posada.santiago.betapostsandcomments.domain.values.Content;

public class Comment extends Entity<CommentId> {

    private Author author;
    private Content content;


    public Comment(CommentId entityId, Author author, Content content) {
        super(entityId);
        this.author = author;
        this.content = content;
    }

    public Author author() {
        return author;
    }

    public Content content() {
        return content;
    }
}

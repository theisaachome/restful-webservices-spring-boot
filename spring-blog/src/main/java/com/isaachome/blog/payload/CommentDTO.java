package com.isaachome.blog.payload;

import lombok.Data;

@Data
public class CommentDTO {
    private long id;
    private String name;
    private String content;
    private String email;
}

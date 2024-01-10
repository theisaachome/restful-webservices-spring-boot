package com.isaachome.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private long id;
   private String title;
    private String description;
    private String content;

}
//public record PostDTO(long id,String title,String description,String content) {
//}

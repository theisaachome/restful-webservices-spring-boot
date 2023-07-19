package com.isaachome.blog.payload;

import java.util.List;

public record PostResponse(List<PostDTO>content,int pageNo,int pageSize,long totalElement,int totalPages,boolean last) {
}

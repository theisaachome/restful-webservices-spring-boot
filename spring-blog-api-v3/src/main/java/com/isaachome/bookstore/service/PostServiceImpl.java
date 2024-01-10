package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.PostDTO;
import com.isaachome.bookstore.entity.Book;
import com.isaachome.bookstore.repos.PostRepos;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepos postRepos;
    private final ModelMapper modelMapper;

    @Override
    public PostDTO getPostById(Long postId) {
        postRepos.findById(postId);
        return null;
    }

    @Override
    public PostDTO createPost(PostDTO dto) {
        Book savedBook = postRepos.save(convertToEntity(dto));
        return convertToDto(savedBook);
    }

    private Book convertToEntity(PostDTO dto){
         return modelMapper.map(dto, Book.class);
//       return new Post(dto.getId(),dto.getTitle(),dto.getDescription(),dto.getContent());
//        return  m;
    }
    private PostDTO convertToDto(Book book){
        return  modelMapper.map(book,PostDTO.class);
    }
}

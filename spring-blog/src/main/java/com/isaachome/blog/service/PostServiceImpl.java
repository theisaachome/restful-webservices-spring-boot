package com.isaachome.blog.service;

import com.isaachome.blog.entity.Category;
import com.isaachome.blog.entity.Post;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.PostDTO;
import com.isaachome.blog.payload.PostResponse;
import com.isaachome.blog.repos.CategoryRepos;
import com.isaachome.blog.repos.PostRepos;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements   PostService{
    private final PostRepos repos;
    private  final CategoryRepos categoryRepos;
    private final ModelMapper mapper;

    public PostServiceImpl(PostRepos repos,ModelMapper mapper, CategoryRepos categoryRepos) {
        this.repos = repos;
        this.mapper=mapper;
        this.categoryRepos =categoryRepos;
    }

    @Override
    public PostDTO createPost(PostDTO data) {
        // find category by ID
        var category =categoryRepos.findById(data.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category","ID",data.getCategoryId()));
        Post post = mapToPost(data);
        post.setCategory(category);
        Post newPost=repos.save(post);
        return mapToDTO(newPost);
    }

    @Override
    public PostResponse  getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> posts = repos.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
       List<PostDTO> contents= listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
       return  new PostResponse(
                contents,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast()
        );
    }

    @Override
    public PostDTO getById(long id) {
        Post post = repos.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO dto, long id) {
        Category category = categoryRepos.findById(dto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category","ID",dto.getCategoryId()));
      if(category!=null){
          Post post = repos.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
          post.setTitle(dto.getTitle());
          post.setDescription(dto.getDescription());
          post.setContent(dto.getContent());
          post.setCategory(category);
          Post updatedPost=repos.save(post);
          return mapToDTO(updatedPost);
      }
      return  null;
    }


    @Override
    public void deletePost(long id) {
      Post post=  repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
      repos.delete(post);
    }

    // get All posts by Category
    @Override
    public List<PostDTO> getPostsByCategoryId(long categoryId) {
        Category category = categoryRepos.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId));
        if(category!=null){
            var postsByCategoryId = repos.findPostsByCategoryId(categoryId);
            return postsByCategoryId.stream().map((element) -> mapper.map(element, PostDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    // convert Entity into DTO
    private  PostDTO mapToDTO(Post post){
//        PostDTO postDTO  = new PostDTO(post.getId(),post.getTitle(),post.getDescription(),post.getContent());
        PostDTO postDTO = mapper.map(post,PostDTO.class);
        return  postDTO;
    }
    // convert DTO into DTO
    private  Post mapToPost(PostDTO dto){
        Post post = mapper.map(dto,Post.class);
//        Post post = new Post();
//        post.setId(dto.id());
//        post.setTitle(dto.title());
//        post.setContent(dto.content());
//        post.setDescription(dto.description());
        return  post;
    }
}

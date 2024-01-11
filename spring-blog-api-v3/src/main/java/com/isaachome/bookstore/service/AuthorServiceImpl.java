package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.AuthorDTO;
import com.isaachome.bookstore.entity.Author;
import com.isaachome.bookstore.exception.ResourceNotFoundException;
import com.isaachome.bookstore.repos.AuthorRepos;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepos authorRepos;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepos authorRepos,ModelMapper modelMapper) {
        this.authorRepos = authorRepos;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO dto) {
       UUID uuid= UUID.randomUUID();
        dto.setUuid(uuid.toString());
       var author = mapToEntity(dto);
       var savedAuthor= authorRepos.save(author);
        return mapToDTO(savedAuthor);
    }

    @Override
    public List<AuthorDTO> getAllAuthor() {
        return authorRepos.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public AuthorDTO getAuthorById(long id) {
       Author author=  authorRepos.findById(id).orElseThrow(()->new ResourceNotFoundException("Author","ID",id));
        return mapToDTO(author);
    }

    @Override
    public AuthorDTO updateAuthor(long author_id, AuthorDTO dto) {
        return null;
    }

    @Override
    public void deleteAuthor(long author_id) {
        // findById
        var author = authorRepos.findById(author_id).orElseThrow(()-> new ResourceNotFoundException("Author","ID",author_id));
        authorRepos.delete(author);
    }

    private Author mapToEntity(AuthorDTO dto){
        return  modelMapper.map(dto,Author.class);
    }
    private AuthorDTO mapToDTO(Author entity){
        return modelMapper.map(entity,AuthorDTO.class);
    }
}

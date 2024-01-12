package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.AuthorDTO;
import com.isaachome.bookstore.dto.AuthorResponse;
import com.isaachome.bookstore.entity.Author;
import com.isaachome.bookstore.exception.ResourceNotFoundException;
import com.isaachome.bookstore.repos.AuthorRepos;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public AuthorResponse getAllAuthor(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
       Page<Author> authorPage = authorRepos.findAll(pageable);
       // get Content from Page
        List<Author> authorList = authorPage.getContent();
        List<AuthorDTO> content = authorList.stream().map(this::mapToDTO).toList();
        AuthorResponse authorResponse= new AuthorResponse();
        authorResponse.setContents(content);
        authorResponse.setPageSize(authorPage.getSize());
        authorResponse.setPageNo(authorPage.getNumber());
        authorResponse.setTotalElement(authorPage.getTotalElements());
        authorResponse.setTotalPage(authorPage.getTotalPages());
        authorResponse.setLast(authorPage.isLast());
        return  authorResponse;
    }

    @Override
    public AuthorDTO getAuthorById(long id) {
       Author author=  authorRepos.findById(id).orElseThrow(()->new ResourceNotFoundException("Author","ID",id));
        return mapToDTO(author);
    }

    @Override
    public AuthorDTO updateAuthor(long author_id, AuthorDTO dto) {
        // find Author
        var author = authorRepos.findById(author_id).orElseThrow(()->new ResourceNotFoundException("Author","ID",author_id));
            author.setId(author_id);
            author.setName(dto.getName());
            author.setFirstName(dto.getFirstName());
            author.setLastName(dto.getLastName());
            author.setAddress(dto.getAddress());
            author.setNational(dto.getNational());
            author.setCountry(dto.getCountry());
//            author.setUuid(d);
        var updatedAuthor = authorRepos.save(author);
        return mapToDTO(updatedAuthor);
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

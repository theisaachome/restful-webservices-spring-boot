package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.BookDTO;
import com.isaachome.bookstore.dto.BookResponse;
import com.isaachome.bookstore.entity.Book;
import com.isaachome.bookstore.exception.ResourceNotFoundException;
import com.isaachome.bookstore.repos.BookRepos;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepos bookRepos;
    private final ModelMapper modelMapper;

    @Override
    public BookDTO getBookById(Long postId) {
       var book= bookRepos.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Book ","ID",postId));
        return convertToDto(book);
    }

    @Override
    public BookDTO createBook(BookDTO dto) {
        Book savedBook = bookRepos.save(convertToEntity(dto));
        return convertToDto(savedBook);
    }

    @Override
    public BookResponse getAllBook(int pageNo, int pageSize, String sortBy, String sortDir) {
        // Sort instance
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        // get Page
        var pageable = PageRequest.of(pageNo,pageSize,sort);
       Page<Book> bookPage= bookRepos.findAll(pageable);
       // get content from page
        List<Book> bookList= bookPage.getContent();
        List<BookDTO> contents = bookList.stream().map(this::convertToDto).toList();
       BookResponse bookResponse= new BookResponse();
       bookResponse.setContents(contents);
       bookResponse.setTotalPage(bookPage.getTotalPages());
       bookResponse.setTotalElement(bookPage.getTotalElements());
       bookResponse.setPageNo(bookPage.getNumberOfElements());
       bookResponse.setPageSize(bookPage.getSize());
       bookResponse.setLast(bookPage.isLast());
        return bookResponse;
    }

    private Book convertToEntity(BookDTO dto){
         return modelMapper.map(dto, Book.class);
    }
    private BookDTO convertToDto(Book book){
        return  modelMapper.map(book, BookDTO.class);
    }
}

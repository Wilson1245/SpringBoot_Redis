package com.example.BooksApi.repositoies;

import com.example.BooksApi.models.BookRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingRepository extends CrudRepository<BookRating, String> {
}

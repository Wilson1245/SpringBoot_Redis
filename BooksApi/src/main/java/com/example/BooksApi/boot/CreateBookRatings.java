package com.example.BooksApi.boot;

import com.example.BooksApi.models.Book;
import com.example.BooksApi.models.BookRating;
import com.example.BooksApi.models.User;
import com.example.BooksApi.repositoies.BookRatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
@Order(4)
@Slf4j
public class CreateBookRatings implements CommandLineRunner {

    @Value("${app.numberOfRatings}")
    private Integer numberOfRatings;

    @Value("${app.ratingStars}")
    private Integer ratingStars;

    private final RedisTemplate<String, String> redisTemplate;

    private final BookRatingRepository bookRepository;

    public CreateBookRatings(RedisTemplate redisTemplate, BookRatingRepository bookRepository){
        this.bookRepository = bookRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0){
            Random random = new Random();
            IntStream.range(0, numberOfRatings).forEach(n -> {
                String bookId = redisTemplate.opsForSet().randomMember(Book.class.getName());
                String userId = redisTemplate.opsForSet().randomMember(User.class.getName());
                int stars = random.nextInt(ratingStars) + 1;

                User user = new User();
                user.setId(userId);

                Book book = new Book();
                book.setId(bookId);

                BookRating rating = BookRating.builder().user(user).book(book).rating(stars).build();
                bookRepository.save(rating);
            });

            log.info(">>>> BookRating created...");

        }
    }
}

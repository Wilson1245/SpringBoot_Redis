package com.example.BooksApi.boot;

import com.example.BooksApi.models.Book;
import com.example.BooksApi.models.Category;
import com.example.BooksApi.repositoies.BookRepository;
import com.example.BooksApi.repositoies.CategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;

@Component
@Order(3)
@Slf4j
public class CreateBooks implements CommandLineRunner {

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    public CreateBooks(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0){
            ObjectMapper mapping = new ObjectMapper();
            mapping.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>() {};

            // File(Path) of List
            List<File> files = Files.list(Paths.get(getClass().getResource("/data/books").toURI()))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .map(java.nio.file.Path::toFile)
                    .collect(Collectors.toList());

            // Categories of Map(HashMap)
            Map<String, Category> categories = new HashMap<>();

            files.forEach(file -> {
                try {
                    log.info(">>>> Processing Book File: " + file.getPath());
                    String categoryName = file.getName().substring(0, file.getName().lastIndexOf("_"));
                    log.info(">>>> Category: " +categoryName);

                    Category category;
                    if(!categories.containsKey(categoryName)) {
                        // Save & Build the Category Name
                        category = Category.builder().name(categoryName).build();
                        categoryRepository.save(category);
                        categories.put(categoryName, category);
                    }else {
                        category = categories.get(categoryName);
                    }

                    InputStream inputStream = new FileInputStream(file);
                    List<Book> books = mapping.readValue(inputStream, typeReference);
                    books.stream().forEach((book) -> {
                        book.addCategory(category);
                        bookRepository.save(book);
                    });
                    log.info(">>>> " + books.size() + " Books Saved!");
                } catch (IOException e) {
                    log.info("Unable to import books: " + e.getMessage());
                }
            });
            log.info(">>>> Loaded Book Data and Created books...");
        }
    }
}

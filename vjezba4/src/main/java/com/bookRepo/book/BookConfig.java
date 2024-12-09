package com.bookRepo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner runner(BookRepository repo) {
        return args -> {
            Book A =new Book(
                    "Babel",
                    "R.F.Kuang",
                    "Fantasy",
                    "Harpercollins",
                    2023,
                    546
            );
            Book B = new Book(
                    "No Longer Human",
                    "Osamu Dazai",
                    "Fiction",
                    "New Directions",
                    1973,
                    176
            );

            repo.saveAll(
                    List.of(A,B)
            );
        };
    }
}

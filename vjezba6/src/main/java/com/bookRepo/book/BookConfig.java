package com.bookRepo.book;

import com.bookRepo.model.Book;
import com.bookRepo.repository.BookRepository;
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
            Book C =new Book(
                    "Hyperion",
                    "Dan Simmons",
                    "Science Fiction",
                    "Doubleday",
                    1989,
                    482
            );
            Book D = new Book(
                    "Mistborn:The Final Empire",
                    "Brandon Sanderson",
                    "Fantasy",
                    "Tor Books",
                    2006,
                    541
            );
            Book E = new Book(
                    "Mistborn:The Well of Ascension",
                    "Brandon Sanderson",
                    "Fantasy",
                    "Tor Books",
                    2007,
                    590
            );
            Book F = new Book(
                    "Mistborn:The Hero of Ages",
                    "Brandon Sanderson",
                    "Fantasy",
                    "Tor Books",
                    2008,
                    572
            );

            repo.saveAll(
                    List.of(A,B,C,D,E,F)
            );
        };
    }
}

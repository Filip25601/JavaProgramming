package com.bookRepo.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table
public class notification {
    @Id
    @SequenceGenerator(
            name = "notification_sequence",
            sequenceName = "notification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence"
    )
    private Long id;
    private String title;
    private String message;
    private String date;
    private String time;
    private String type;
    private String url;
}

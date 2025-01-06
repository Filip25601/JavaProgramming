package com.bookRepo.service;

import com.bookRepo.model.*;
import com.bookRepo.model.Book;
import com.bookRepo.model.Reservation;
import com.bookRepo.repository.NotificationRepository;
import com.bookRepo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReservationService {
     private final ReservationRepository reservationRepository;
     private final NotificationService notificationService;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, NotificationService notificationService) {
        this.reservationRepository = reservationRepository;
        this.notificationService = notificationService;
    }

    public void createReservation(Reservation reservation) {
        Reservation reservation1 = new Reservation();
        reservation1.setReservationDate(LocalDate.now().toString());
        reservation1.setReservationTime(LocalTime.now().toString());
        reservation1.setReservationStatus("Pending");

        Book book= fetchBookById(reservation.getBook().getId());
        Member member = fetchMemberById(reservation.getMember().getId());
        reservation1.setBook(book);
        reservation1.setMember(member);
        reservationRepository.save(reservation);
    }

    public void fulfillReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(()-> new RuntimeException("Reservation not found"));
            reservation.setReservationStatus("Fulfilled");
            reservationRepository.save(reservation);
            String message = String.format("Reservation fulfilled with id %d", id);
            notificationService.createNotification(reservation.getMember(),message);
    }
    private Book fetchBookById(Long bookId) {
        // Mock method to fetch book; implement repository call
        return new Book();
    }

    private Member fetchMemberById(Long memberId) {
        // Mock method to fetch member; implement repository call
        return new Member();
    }
}

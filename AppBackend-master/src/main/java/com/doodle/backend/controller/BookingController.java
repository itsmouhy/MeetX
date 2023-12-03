package com.doodle.backend.controller;


import com.doodle.backend.entities.Booking;
import com.doodle.backend.entities.User;
import com.doodle.backend.services.BookingServiceImp;
import com.doodle.backend.services.UserServiceImp;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class BookingController {
    @Autowired
    private BookingServiceImp bookingServiceImpl;

    @Autowired
    private UserServiceImp userServiceImp;
    @GetMapping("/bookings")
    public List<Booking> listBooking() {
        return bookingServiceImpl.getAllBookings();
    }


    @PostMapping("/create")
    public ResponseEntity<Booking> createEvent(@RequestBody Booking booking) {
        Booking createdEvent = bookingServiceImpl.createEvent(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
    @GetMapping("/booking/{IdBooking}")
    public Booking getEventById(@PathVariable Long IdBooking) {
        return bookingServiceImpl.getEventById(IdBooking);
    }


    @DeleteMapping("/deleteBooking/{IdBooking}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long IdBooking) {
        bookingServiceImpl.deleteBooking(IdBooking);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @PostMapping("/updateBooking/{IdBooking}")
    public ResponseEntity<String> updateEvent(@PathVariable Long IdBooking, @RequestBody Map<String, Object> updates) {
        boolean updated = bookingServiceImpl.updateEvent(IdBooking, (Map<String, Object>) updates);

        if (updated) {
            return ResponseEntity.ok("Event updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }

    @PostMapping("/bookEvent")
    public ResponseEntity<String> bookEvent(@RequestParam Long IdBooking, @RequestParam Long IdUser) {
        try {

            bookingServiceImpl.bookEvent(IdBooking, IdUser);

            return ResponseEntity.ok("Booking successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking failed");
        }
    }


    @GetMapping ("BookByUser/{IdBooking}/{IdUser}")
    public boolean BookByUser(@PathVariable Long IdBooking,@PathVariable Long IdUser){

        Booking booking=bookingServiceImpl.getEventById(IdBooking);
        User user=userServiceImp.getUser(IdUser);

        if(booking.getBUsers().contains(user)){
            return true;
        }else{
            return false;
        }
    }



}

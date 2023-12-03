package com.doodle.backend.services;


import com.doodle.backend.entities.Booking;
import com.doodle.backend.entities.User;
import com.doodle.backend.repositories.BookingRepository;
import com.doodle.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService{

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteBooking(Long IdBooking) {
        bookingRepository.deleteById(IdBooking);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @Override
    public boolean updateEvent(Long IdBooking, Map<String, Object> updates) {
        Optional<Booking> optionalEvent = bookingRepository.findById(IdBooking);

        if (optionalEvent.isPresent()) {
            Booking existingEvent = optionalEvent.get();

            // Check if IdBooking of existingEvent matches the provided IdBooking
            if (existingEvent.getIdBooking().equals(IdBooking)) {
                if (updates.containsKey("name")) {
                    existingEvent.setName((String) updates.get("name"));
                }
                if (updates.containsKey("place")) {
                    existingEvent.setPlace((String) updates.get("place"));
                }
                if (updates.containsKey("day")) {
                    existingEvent.setDay(LocalDate.parse((String) updates.get("day")));
                }
                if (updates.containsKey("startHour")) {
                    existingEvent.setStartHour(LocalTime.parse((String) updates.get("startHour")));
                }
                if (updates.containsKey("endHour")) {
                    existingEvent.setEndHour(LocalTime.parse((String) updates.get("endHour")));
                }
                if (updates.containsKey("description")) {
                    existingEvent.setDescription((String) updates.get("description"));
                }
                if (updates.containsKey("placesAvailable")) {
                    Object placesAvailableValue = updates.get("placesAvailable");
                    if (placesAvailableValue instanceof Integer) {
                        existingEvent.setPlacesAvailable((Integer) placesAvailableValue);
                    } else if (placesAvailableValue instanceof String) {
                        try {
                            existingEvent.setPlacesAvailable(Integer.parseInt((String) placesAvailableValue));
                        } catch (NumberFormatException e) {
                            // Handle the case where the string cannot be parsed to an integer
                            // You can log an error or take appropriate action here.
                        }
                    }
                }

                if (updates.containsKey("imageFilePath")) {
                    existingEvent.setImageFilePath((String) updates.get("imageFilePath"));
                }

                bookingRepository.save(existingEvent);
                return true;
            } else {
                return false; // Provided IdBooking doesn't match the event's IdBooking
            }
        } else {
            return false; // Event not found
        }
    }

    @Override
    public Booking createEvent(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getEventById(Long IdBooking) {
        return bookingRepository.findById(IdBooking).get();
    }

    @Transactional
    public void bookEvent(Long IdBooking, Long IdUser) {
        // Fetch the booking and user entities
        Booking booking = bookingRepository.findById(IdBooking)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        User user = userRepository.findById(IdUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
            booking.getBUsers().add(user);
            bookingRepository.save(booking);


    }
}

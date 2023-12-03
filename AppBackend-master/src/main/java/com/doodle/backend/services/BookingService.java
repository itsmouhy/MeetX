package com.doodle.backend.services;

import com.doodle.backend.entities.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public interface BookingService {

    List<Booking> getAllBookings();

    public ResponseEntity<String> deleteBooking(@PathVariable Long IdBooking);

    boolean updateEvent(Long IdBooking, Map<String, Object> updates);

    public Booking createEvent(Booking booking);

    public Booking getEventById(@PathVariable Long IdBooking);

    public void bookEvent(Long IdBooking, Long IdUser);
}

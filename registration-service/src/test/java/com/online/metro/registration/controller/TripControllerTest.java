package com.online.metro.registration.controller;

import com.online.metro.registration.dto.PassengerType;
import com.online.metro.registration.dto.PaymentType;
import com.online.metro.registration.dto.PriceDTO;
import com.online.metro.registration.dto.StationDTO;
import com.online.metro.registration.dto.TicketDTO;
import com.online.metro.registration.dto.TripType;
import com.online.metro.registration.service.BookingService;
import com.online.metro.registration.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class TripControllerTest extends AbstractTest {
    @MockBean
    private TripService tripService;
    @MockBean
    private BookingService bookingService;
    @Autowired
    private MockMvc mockMvc;

}
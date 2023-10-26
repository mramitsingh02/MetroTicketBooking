package com.online.metro.controller;

import com.online.metro.dto.PassengerType;
import com.online.metro.dto.PaymentType;
import com.online.metro.dto.PriceDTO;
import com.online.metro.dto.StationDTO;
import com.online.metro.dto.TicketDTO;
import com.online.metro.dto.TripDTO;
import com.online.metro.dto.TripType;
import com.online.metro.service.BookingService;
import com.online.metro.service.TripService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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


    @Test
    void createTrip() throws Exception {
        String path = "http://localhost:6666/trip/";
        final Long from = 1001L;
        final Long to = 1005L;
        TicketDTO ticketDTO = getTicketDTO(from, to);
        final PriceDTO priceDTO = PriceDTO.builder().fair(200d).source(StationDTO.of(from)).destination(StationDTO.of(to)).distance(14).build();
        final TicketDTO persistTicketDTO = getTicketDTO(from, to);
        persistTicketDTO.setTicketId(1000l);
        persistTicketDTO.setPrice(priceDTO.getFair());
        persistTicketDTO.setPaymentType(PaymentType.CASH);
        persistTicketDTO.setTripType(TripType.NOT_TO_START);

        when(bookingService.findByTicketId(persistTicketDTO.getTicketId())).thenReturn(persistTicketDTO);
        final TripDTO tripDTO = new TripDTO(persistTicketDTO);
        final TripDTO pesistanceTripDTO = new TripDTO(persistTicketDTO);
        pesistanceTripDTO.setTripId(1l);
        when(tripService.saveTrip(tripDTO)).thenReturn(pesistanceTripDTO);

        String newCustomerJson = mapToJson(tripDTO);
        MockHttpServletResponse response = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCustomerJson)).andReturn().getResponse();
        assertEquals(201, response.getStatus());

    }

    private TicketDTO getTicketDTO(final Long from, final Long to) {
        return TicketDTO.builder()
                .bookingStationId(from)
                .sourceStationId(from)
                .destinationStationId(to)
                .passengerType(PassengerType.ANONYMOUS)
                .build();
    }

    @Test
    void startTrip() throws Exception {
        String path = "http://localhost:6666/trip/1000/start";
        final Long from = 1001L;
        final Long to = 1005L;
        final PriceDTO priceDTO = PriceDTO.builder().fair(200d).source(StationDTO.of(from)).destination(StationDTO.of(to)).distance(14).build();
        final TicketDTO persistTicketDTO = getTicketDTO(from, to);
        persistTicketDTO.setTicketId(1000l);
        persistTicketDTO.setPrice(priceDTO.getFair());
        persistTicketDTO.setPaymentType(PaymentType.CASH);
        persistTicketDTO.setTripType(TripType.NOT_TO_START);

        when(bookingService.findByTicketId(persistTicketDTO.getTicketId())).thenReturn(persistTicketDTO);
        final TripDTO tripDTO = new TripDTO(persistTicketDTO);
        final TripDTO pesistanceTripDTO = new TripDTO(persistTicketDTO);
        pesistanceTripDTO.setTripId(1l);
        when(tripService.saveTrip(tripDTO)).thenReturn(pesistanceTripDTO);

        MockHttpServletResponse response = mockMvc.perform(put(path)
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(200, response.getStatus());

    }

    @Test
    void stopTrip() throws Exception {
        String path = "http://localhost:6666/trip/1000/stop";
        final Long from = 1001L;
        final Long to = 1005L;
        final PriceDTO priceDTO = PriceDTO.builder().fair(200d).source(StationDTO.of(from)).destination(StationDTO.of(to)).distance(14).build();
        final TicketDTO persistTicketDTO = getTicketDTO(from, to);
        persistTicketDTO.setTicketId(1000l);
        persistTicketDTO.setPrice(priceDTO.getFair());
        persistTicketDTO.setPaymentType(PaymentType.CASH);
        persistTicketDTO.setTripType(TripType.NOT_TO_START);

        when(bookingService.findByTicketId(persistTicketDTO.getTicketId())).thenReturn(persistTicketDTO);
        final TripDTO tripDTO = new TripDTO(persistTicketDTO);
        final TripDTO pesistanceTripDTO = new TripDTO(persistTicketDTO);
        pesistanceTripDTO.setTripId(1l);
        when(tripService.findTripByTicketId(pesistanceTripDTO.getTicketId())).thenReturn(pesistanceTripDTO);

        MockHttpServletResponse response = mockMvc.perform(put(path)
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(200, response.getStatus());

    }
}
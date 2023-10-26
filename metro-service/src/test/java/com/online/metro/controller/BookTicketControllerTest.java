package com.online.metro.controller;

import com.online.metro.dto.PassengerType;
import com.online.metro.dto.PaymentType;
import com.online.metro.dto.PriceDTO;
import com.online.metro.dto.StationDTO;
import com.online.metro.dto.TicketDTO;
import com.online.metro.dto.TripType;
import com.online.metro.service.BookingService;
import com.online.metro.service.FairCalculateService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class BookTicketControllerTest extends AbstractTest {
    @MockBean
    private BookingService bookingService;
    @MockBean
    private FairCalculateService fairCalculateService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTicketBooking() throws Exception {
        String path = "http://localhost:6666/ticket/";
        final Long from = 1001L;
        final Long to = 1005L;
        TicketDTO ticketDTO = getTicketDTO(from, to);
        final PriceDTO priceDTO = PriceDTO.builder().fair(200d).source(StationDTO.of(from)).destination(StationDTO.of(to)).distance(14).build();
        when(fairCalculateService.getFair(PassengerType.ANONYMOUS, from, to)).thenReturn(priceDTO);
        final TicketDTO persistTicketDTO = getTicketDTO(from, to);
        persistTicketDTO.setPrice(priceDTO.getFair());
        persistTicketDTO.setPaymentType(PaymentType.CASH);
        persistTicketDTO.setTripType(TripType.NOT_TO_START);

        when(bookingService.saveTicket(ticketDTO)).thenReturn(persistTicketDTO);
        String newCustomerJson = mapToJson(ticketDTO);
        MockHttpServletResponse response = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCustomerJson)).andReturn().getResponse();
        assertEquals(201, response.getStatus());
        verify(fairCalculateService).getFair(any(), anyLong(), anyLong());

    }

    @Test
    void testGetTicket() throws Exception {
        String path = "http://localhost:6666/ticket/1";

        final Long from = 1001L;
        final Long to = 1005L;
        TicketDTO ticketDTO = getTicketDTO(from, to);
        final TicketDTO persistTicketDTO = getTicketDTO(from, to);
        persistTicketDTO.setPrice(200d);
        persistTicketDTO.setPaymentType(PaymentType.CASH);
        persistTicketDTO.setTripType(TripType.NOT_TO_START);
        when(bookingService.findByTicketId(anyLong())).thenReturn(persistTicketDTO);

        MockHttpServletResponse response = mockMvc.perform(get(path).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        assertEquals(mapToJson(persistTicketDTO), response.getContentAsString());

    }


    private TicketDTO getTicketDTO(final Long from, final Long to) {
        return TicketDTO.builder()
                .bookingStationId(from)
                .sourceStationId(from)
                .destinationStationId(to)
                .passengerType(PassengerType.ANONYMOUS)
                .build();
    }

}
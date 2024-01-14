package ru.netology.domain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;
import ru.netology.domain.AviaSouls;
import ru.netology.domain.Ticket;

import java.util.Comparator;

public class AviaSoulsTest {
    AviaSouls manager = new AviaSouls();
    Ticket ticket1 = new Ticket(
            "Moscow",
            "Saint-Petersburg",
            7000,
            10,
            12
    );
    Ticket ticket2 = new Ticket(
            "Saint-Petersburg",
            "Moscow",
            5500,
            13,
            15
    );
    Ticket ticket3 = new Ticket(
            "Saint-Petersburg",
            "Sochi",
            5500,
            16,
            20
    );
    Ticket ticket4 = new Ticket(
            "Moscow",
            "Krasnodar",
            9000,
            8,
            11
    );
    Ticket ticket5 = new Ticket(
            "Saint-Petersburg",
            "Sochi",
            10000,
            11,
            13
    );


    @Test
    public void shouldCompareToTicketsIfSecondCheaper() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        int expected = 1;
        int actual = ticket1.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToTicketsIfFirstCheaper() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        int expected = -1;
        int actual = ticket4.compareTo(ticket5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToTicketsIfTheyAreEquals() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        int expected = 0;
        int actual = ticket3.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortSearchInPriceSeveralTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket3, ticket5};
        Ticket[] actual = manager.search("Saint-Petersburg", "Sochi");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortSearchInPriceOneTicket() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.search("Moscow", "Krasnodar");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortSearchInPriceZeroTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Moscow", "Ufa");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldComparatorIfSecondLess() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        TicketTimeComparator timeComparator = new TicketTimeComparator();

        int expected = 1;
        int actual = timeComparator.compare(ticket3, ticket4);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldComparatorIfFirsLess() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        TicketTimeComparator timeComparator = new TicketTimeComparator();

        int expected = -1;
        int actual = timeComparator.compare(ticket2, ticket3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldComparatorIfTicketsAreEquals() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        TicketTimeComparator timeComparator = new TicketTimeComparator();

        int expected = 0;
        int actual = timeComparator.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortSeveralTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket5, ticket3};
        Ticket[] actual = manager.searchAndSortBy("Saint-Petersburg", "Sochi", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortOneTicket() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.searchAndSortBy("Saint-Petersburg", "Moscow", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortZeroTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("Saint-Petersburg", "Nadym", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void shouldCompareTicketByPriceIfT2moreT1() {

        Ticket t1 = new Ticket("VOG", "MSK", 10_000, 5, 7);
        Ticket t2 = new Ticket("VOG", "MSK", 15_000, 7, 9);

        assertTrue(t1.compareTo(t2) < 0);
    }

    @Test
    public void shouldCompareTicketByPriceIfT1moreT2() {

        Ticket t1 = new Ticket("VOG", "MSK", 15_000, 5, 7);
        Ticket t2 = new Ticket("VOG", "MSK", 10_000, 7, 9);

        assertTrue(t1.compareTo(t2) > 0);
    }

    @Test
    public void shouldCompareTicketByPriceT1EqualT2() {

        Ticket t1 = new Ticket("VOG", "MSK", 10_000, 5, 7);
        Ticket t2 = new Ticket("VOG", "MSK", 10_000, 7, 9);

        assertEquals(0, t1.compareTo(t2));
    }

    @Test
    public void shouldReturnAllTickets() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("VOG", "MSK", 8_000, 9, 11);
        Ticket t2 = new Ticket("VOG", "MSK", 5_000, 8, 10);
        Ticket t3 = new Ticket("VOG", "MSK", 6_000, 10, 12);
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        Ticket[] expected = {t1, t2, t3};
        Ticket[] actual = manager.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldReturnOneTicket() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket = new Ticket("KZN", "MSK", 9000, 14, 16);
        manager.add(ticket);

        Ticket[] result = manager.search("KZN", "MSK");
        assertArrayEquals(new Ticket[]{ticket}, result);
    }

    @Test
    public void shouldSearchTicketsByTimeToFlight() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("VOG", "SPB", 10_000, 8, 11); // 3 часа
        Ticket t2 = new Ticket("VOG", "SPB", 10_000, 7, 9);  // 2 часа
        Ticket t3 = new Ticket("VOG", "SPB", 10_000, 6, 10); // 4 часа
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] expected = {t2, t1, t3};
        Ticket[] actual = manager.searchAndSortBy("VOG", "SPB", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketByPriceInSearch() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("VOG", "MSK", 8_000, 9, 11);
        Ticket t2 = new Ticket("VOG", "MSK", 5_000, 8, 10);
        Ticket t3 = new Ticket("VOG", "MSK", 6_000, 10, 12);
        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        Ticket[] expected = {t2, t3, t1};
        Ticket[] actual = manager.search("VOG", "MSK");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldReturnEmptyArrayIfNotMatch() {
        AviaSouls manager = new AviaSouls();
        Ticket t1 = new Ticket("VOG", "MSK", 5000, 8, 10);
        manager.add(t1);

        Ticket[] actual = manager.search("SPB", "KZN");

        assertEquals(0, actual.length);
    }

    @Test
    public void shouldCompareTicketByTimeIfT2moreT1() {

        Ticket t1 = new Ticket("VOG", "MSK", 10_000, 5, 7);
        Ticket t2 = new Ticket("VOG", "MSK", 10_000, 6, 9);

        TicketTimeComparator comp = new TicketTimeComparator();

        assertTrue(comp.compare(t1,t2) < 0);
    }

    @Test
    public void shouldCompareTicketByTimeIfT1moreT2() {

        Ticket t1 = new Ticket("VOG", "MSK", 15_000, 6, 9);
        Ticket t2 = new Ticket("VOG", "MSK", 10_000, 5, 7);
        TicketTimeComparator comp = new TicketTimeComparator();

        assertTrue(comp.compare(t1,t2) > 0);
    }

    @Test
    public void shouldCompareTicketByTimeT1EqualT2() {

        Ticket t1 = new Ticket("VOG", "MSK", 10_000, 5, 7);
        Ticket t2 = new Ticket("VOG", "MSK", 15_000, 5, 7);
        TicketTimeComparator comp = new TicketTimeComparator();

        assertEquals(0, comp.compare(t1, t2));
    }

}

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        int first = t1.getTimeTo() - t1.getTimeFrom();
        int second = t2.getTimeTo() - t2.getTimeFrom();

        if (first < second) {// ваш код
            return -1;
        } else if (first > second) {
            return 1;
        }
        return 0;
    }
}
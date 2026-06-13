import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Library {
    private final Map<String, Book> catalog = new LinkedHashMap<>();
    private final Map<String, Queue<String>> waitlists = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();

    public void addBook(Book book) {
        catalog.put(book.getTitle(), book);
        waitlists.put(book.getTitle(), new LinkedList<>());
    }

    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    /**
     * TODO: lihat PROBLEM.md poin 1.
     */
    public void borrowBook(String title, String memberId) {
        // TODO
    }

    /**
     * TODO: lihat PROBLEM.md poin 2.
     */
    public void returnBook(String title) {
        // TODO
    }

    /**
     * TODO [Stream]: kelompokkan catalog berdasarkan genre, hitung jumlahnya.
     */
    public Map<String, Long> countBooksByGenre() {
        return null;
    }

    /**
     * TODO [Stream]: n genre dengan jumlah buku terbanyak (descending),
     * seri diurutkan alfabetis.
     */
    public List<String> topGenres(int n) {
        return null;
    }

    /**
     * TODO [Stream]: rata-rata tahun terbit buku pada genre tertentu.
     */
    public double averageYearByGenre(String genre) {
        return 0;
    }

    /**
     * TODO [Stream]: id member yang borrowedCount melebihi getMaxBorrowLimit().
     */
    public List<String> membersOverLimit() {
        return null;
    }
}

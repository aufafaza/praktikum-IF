import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.Comparator;

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
    }

    /**
     * TODO: lihat PROBLEM.md poin 2.
     */
    public void returnBook(String title) {
    }

    /**
     * TODO [Stream]: kelompokkan catalog berdasarkan genre, hitung jumlahnya.
     */
    public Map<String, Long> countBooksByGenre() {
    }

    /**
     * TODO [Stream]: n genre dengan jumlah buku terbanyak (descending),
     * seri diurutkan alfabetis.
     */
    public List<String> topGenres(int n) {
    }

    /**
     * TODO [Stream]: rata-rata tahun terbit buku pada genre tertentu.
     */
    public double averageYearByGenre(String genre) {
    }

    /**
     * TODO [Stream]: id member yang borrowedCount melebihi getMaxBorrowLimit().
     */
    public List<String> membersOverLimit() {
    }

    /**
     * TODO [Stream]: lihat PROBLEM.md poin 7.
     * Map title -> jumlah anggota di waitlist, hanya untuk title yang
     * waitlist-nya tidak kosong.
     */
    public Map<String, Integer> waitlistSizes() {

    }

    /**
     * TODO [Stream]: lihat PROBLEM.md poin 8.
     * Daftar title yang punya waitlist tidak kosong, diurutkan alfabetis.
     */
    public List<String> titlesWithWaitlist() {
        return null;
    }

    /**
     * TODO [Stream]: lihat PROBLEM.md poin 9.
     * Title dengan waitlist terpanjang (seri -> alfabetis pertama),
     * atau "NONE" jika semua waitlist kosong.
     */
    public String longestWaitlistTitle() {
        return null;
    }

    /**
     * TODO [Stream]: lihat PROBLEM.md poin 10.
     * Salinan urutan memberId pada waitlist `title` (tanpa mengubah queue
     * aslinya).
     */
    public List<String> waitlistSnapshot(String title) {
        return null;
    }
}

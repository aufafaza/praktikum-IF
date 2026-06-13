import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

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

    public void borrowBook(String title, String memberId) {
        Book book = catalog.get(title);
        if (book == null) {
            System.out.println("DENIED " + memberId + " " + title + " NOT_FOUND");
            return;
        }

        if (!book.isBorrowed()) {
            Member member = members.get(memberId);
            if (member.getBorrowedCount() >= member.getMaxBorrowLimit()) {
                System.out.println("DENIED " + memberId + " " + title + " LIMIT_REACHED");
                return;
            }
            book.setBorrowed(true);
            member.incrementBorrowed();
            System.out.println("BORROWED " + title + " by " + memberId);
        } else {
            waitlists.get(title).add(memberId);
            System.out.println("WAITLISTED " + memberId + " for " + title);
        }
    }

    public void returnBook(String title) {
        Book book = catalog.get(title);
        book.setBorrowed(false);
        System.out.println("RETURNED " + title);

        Queue<String> queue = waitlists.get(title);
        if (!queue.isEmpty()) {
            String nextMemberId = queue.poll();
            borrowBook(title, nextMemberId);
        }
    }

    public Map<String, Long> countBooksByGenre() {
        return catalog.values().stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
    }

    public List<String> topGenres(int n) {
        return countBooksByGenre().entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed()
                        .thenComparing(Map.Entry::getKey))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public double averageYearByGenre(String genre) {
        return catalog.values().stream()
                .filter(b -> b.getGenre().equals(genre))
                .mapToInt(Book::getYear)
                .average()
                .orElse(0);
    }

    public List<String> membersOverLimit() {
        return members.values().stream()
                .filter(m -> m.getBorrowedCount() > m.getMaxBorrowLimit())
                .map(Member::getId)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> waitlistSizes() {
        return waitlists.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }

    public List<String> titlesWithWaitlist() {
        return waitlists.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

    public String longestWaitlistTitle() {
        return waitlists.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .max(Comparator.<Map.Entry<String, Queue<String>>>comparingInt(e -> e.getValue().size())
                        .thenComparing(Map.Entry::getKey, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .orElse("NONE");
    }

    public List<String> waitlistSnapshot(String title) {
        return waitlists.get(title).stream().collect(Collectors.toList());
    }
}

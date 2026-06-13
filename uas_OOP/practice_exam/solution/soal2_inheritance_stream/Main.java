import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String cmd = sc.next();
            switch (cmd) {
                case "ADD_BOOK": {
                    String[] p = sc.next().split("\\|");
                    Author author = new Author(p[1], p[2]);
                    library.addBook(new Book(p[0], author, p[3], Integer.parseInt(p[4])));
                    break;
                }
                case "ADD_MEMBER": {
                    String[] p = sc.next().split("\\|");
                    Member member = p[2].equals("PREMIUM")
                            ? new PremiumMember(p[0], p[1])
                            : new RegularMember(p[0], p[1]);
                    library.addMember(member);
                    break;
                }
                case "BORROW": {
                    String[] p = sc.next().split("\\|");
                    library.borrowBook(p[0], p[1]);
                    break;
                }
                case "RETURN": {
                    String title = sc.next();
                    library.returnBook(title);
                    break;
                }
                case "GENRE_STATS": {
                    Map<String, Long> stats = library.countBooksByGenre();
                    System.out.println(stats);
                    break;
                }
                case "TOP_GENRES": {
                    int n = sc.nextInt();
                    List<String> top = library.topGenres(n);
                    System.out.println(top);
                    break;
                }
                case "AVG_YEAR": {
                    String genre = sc.next();
                    System.out.println(library.averageYearByGenre(genre));
                    break;
                }
                case "WAITLIST_SIZES": {
                    System.out.println(library.waitlistSizes());
                    break;
                }
                case "WAITLIST_TITLES": {
                    System.out.println(library.titlesWithWaitlist());
                    break;
                }
                case "LONGEST_WAITLIST": {
                    System.out.println(library.longestWaitlistTitle());
                    break;
                }
                case "WAITLIST": {
                    String title = sc.next();
                    System.out.println(library.waitlistSnapshot(title));
                    break;
                }
                case "END":
                    sc.close();
                    return;
                default:
                    break;
            }
        }
        sc.close();
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class BoardGameAnalytics {

    private List<BoardGame> games;
    private Set<String> players;
    private Map<String, Integer> stockByGame;
    private Map<String, List<Integer>> ratings;

    public BoardGameAnalytics() {
        this.games = new ArrayList<>();
        this.players = new HashSet<>();
        this.stockByGame = new HashMap<>();
        this.ratings = new HashMap<>();
    }

    /**
     * Menambahkan game baru beserta stok awal.
     * Jika game dengan nama sama sudah ada, stoknya ditambah.
     *
     * @param game         board game yang ditambahkan
     * @param initialStock stok awal yang ditambahkan
     */
    public void addGame(BoardGame game, int initialStock) {
        if (!games.contains(game)) {
            games.add(game);
        }
        stockByGame.put(game.getName(), stockByGame.getOrDefault(game.getName(), 0) + initialStock);
    }

    /**
     * Menambahkan rating dari seorang pemain untuk sebuah game.
     * Pemain disimpan sebagai pemain unik.
     *
     * @param gameName   nama game
     * @param playerName nama pemain
     * @param rating     nilai rating
     */
    public void addRating(String gameName, String playerName, int rating) {
        players.add(playerName);
        ratings.computeIfAbsent(gameName, k -> new ArrayList<>()).add(rating);
    }

    /**
     * Menghitung rata-rata rating sebuah game.
     * Jika belum ada rating, hasilnya 0.0.
     *
     * @param gameName nama game
     * @return rata-rata rating
     */
    private double getAverageRating(String gameName) {
        List<Integer> gameRatings = ratings.get(gameName);
        if (gameRatings == null || gameRatings.isEmpty())
            return 0.0;
        return gameRatings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    /**
     * Mengembalikan daftar nama game yang stoknya kurang dari threshold.
     * Hasil diurutkan berdasarkan stok menaik.
     * Jika stok sama, urutkan berdasarkan nama game secara alfabetis.
     *
     * Method ini diharapkan menggunakan Map dan Stream API.
     *
     * @param threshold batas stok
     * @return daftar nama game dengan stok di bawah threshold
     */
    public List<String> getLowStockGames(int threshold) {
        return stockByGame.entrySet().stream()
                .filter(entry -> entry.getValue() < threshold)
                .sorted((e1, e2) -> {
                    int stockCompare = e1.getValue().compareTo(e2.getValue());
                    return (stockCompare != 0) ? stockCompare : e1.getKey().compareTo(e2.getKey());
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan daftar nama game yang:
     * - cocok untuk jumlah pemain tertentu
     * - memiliki rata-rata rating minimal tertentu
     * Hasil diurutkan alfabetis.
     *
     * @param playerCount jumlah pemain
     * @param minRating   rating minimum
     * @return daftar nama game yang direkomendasikan
     */
    public List<String> getRecommendedGames(int playerCount, double minRating) {
        return games.stream()
                .filter(g -> playerCount >= g.getMinPlayers() && playerCount <= g.getMaxPlayers())
                .filter(g -> getAverageRating(g.getName()) >= minRating)
                .map(BoardGame::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Main untuk mengetes implementasi secara lokal.
     *
     * Format input:
     * N
     * name minPlayers maxPlayers playTime category stock (sebanyak N baris)
     * R
     * gameName playerName rating (sebanyak R baris)
     * threshold
     * playerCount minRating
     *
     * Output:
     * Baris 1:
     * LOW_STOCK <name1> <name2> ...
     * atau
     * LOW_STOCK -
     *
     * Baris 2:
     * RECOMMENDED <name1> <name2> ...
     * atau
     * RECOMMENDED -
     */
}

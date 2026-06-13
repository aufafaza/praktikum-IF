public abstract class Member {
    protected final String id;
    protected final String name;
    protected int borrowedCount = 0;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void incrementBorrowed() {
        borrowedCount++;
    }

    public void decrementBorrowed() {
        borrowedCount--;
    }

    public abstract int getMaxBorrowLimit();
}

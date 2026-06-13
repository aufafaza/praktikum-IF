public class PremiumMember extends Member {
    public PremiumMember(String id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxBorrowLimit() {
        return 10;
    }
}

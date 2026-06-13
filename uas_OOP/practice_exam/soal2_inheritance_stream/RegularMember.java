public class RegularMember extends Member {
    public RegularMember(String id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxBorrowLimit() {
        return 3;
    }
}

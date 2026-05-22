public final class ReaderType {
    // Object cố định để đại diện cho 2 loại Reader
    public static final ReaderType SINH_VIEN = new ReaderType("SINH_VIEN", 3);
    public static final ReaderType GIANG_VIEN = new ReaderType("GIANG_VIEN", 5);

    private final String name;
    private final int borrowLimit;

    // Private constructor để ngăn ngừa việc tạo obj mới
    private ReaderType(String name, int borrowLimit) {
        this.name = name;
        this.borrowLimit = borrowLimit;
    }

    public String getName() {
        return name;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public static String nameOf(ReaderType type) {
        return type != null ? type.getName() : "Unknown";
    }
}

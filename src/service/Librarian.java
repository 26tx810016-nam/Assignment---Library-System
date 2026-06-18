package service;

import java.time.LocalDate;

import model.Reader;
import model.Book;
import model.BorrowSlip;
import model.Fine;

public class Librarian {
    private String librarianID;
    private String name;

    public Librarian(String librarianID, String name) {
        this.librarianID = librarianID;
        this.name = name;
    }

    public String getLibrarianID() {
        return librarianID;
    }

    public String getName() {
        return name;
    }


    // Cho phep Reader muon sach neu du dieu kien, tao BorrowSlip va cap nhat so luong sach
    public BorrowSlip lendBook(Reader reader, Book book, LocalDate borrowDate, LocalDate returnDate) {
        if (!reader.canBorrow()) {
            System.out.println("Ban da dat gioi han muon sach.");
            return null;
        }

        if (book.getQuantity() == 0) {
            System.out.println("Sach ban muon da het.");
            return null;
        }

        BorrowSlip slip = new BorrowSlip(reader, book);

        // Cap nhat so luong sach doc gia muon va tru so luong sach trong kho
        reader.borrowOne();
        book.decreaseQuantity();

        System.out.println("Muon sach thanh cong");
        return slip;
    }

    // Nhan sach tra ve, tinh tien phat neu tra tre, cap nhat BorrowSlip va tang so luong sach
    public void receiveBook(BorrowSlip slip, LocalDate returnDate) {
        
        // Lay ngay tra du kien tu phieu muon
        LocalDate dueDate = LocalDate.parse(slip.getReturnDate());

        // Tinh tien phat tu ngay du kien den ngay tra thuc te
        if (returnDate.isAfter(dueDate)) {
            int totalFine = Fine.calculateFine(dueDate, returnDate);

            if (totalFine > 0) {
                System.out.println("Phi tra tre: " + totalFine + " VND");
                System.out.println("Xin lien he thu thu de thanh toan tien phat");
            }
        }

        // Cap nhat ngay tra du kien bang null de danh dau da tra sach
        slip.setReturnDate(null);

        // Tang so luong sach trong kho
        slip.getBook().increaseQuantity();

        // Cap nhat so luong sach doc gia muon va tang so luong sach trong kho
        slip.getReader().returnOne();
        slip.getBook().increaseQuantity();

        System.out.println("Tra sach thanh cong");
    }

    public void showLibrarianInfo() {
        System.out.println("Thu Thu ID: " + librarianID);
        System.out.println("Ten: " + name);
    }
}

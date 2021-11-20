package dao;

import dto.*;
import java.util.*;
public interface RentalDao {
	
	boolean insertRental(Book book);
	boolean updateRental(Book book); // 대출기간 수정
	boolean patchRental(Book book); // 반납 처리
	List<Rental> selectRentals(int bookId);
	List<Rental> selectRentals(String email);
	List<Rental> selectRental(int rentalId);
	
}

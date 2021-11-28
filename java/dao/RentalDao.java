package dao;

import dto.*;
import java.util.*;
public interface RentalDao {
	
	boolean insertRental(String userID, Book book);
	boolean updateRental(String userID, Book book); // 대출기간 수정
	boolean patchRental(String userID, Book book); // 반납 처리
	List<Rental> selectRentals(int bookId);
	List<Rental> selectRentals(String userID);
	List<Rental> selectRental(int rentalId);
	
}

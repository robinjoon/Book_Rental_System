package dao;

import dto.*;
import java.util.*;
public interface RentalDao {
	
	boolean insertRental(Rental rental);
	boolean updateRental(Rental rental); // 대출기간 수정
	boolean patchRental(Rental rental); // 반납 처리
	List<Rental> selectRentals(int bookId);
	List<Rental> selectRentals(String userID);
	Rental selectRental(int rentalId);
	
}

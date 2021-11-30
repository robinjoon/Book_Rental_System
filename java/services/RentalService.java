package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.RentalDao;
import dto.Rental;

public class RentalService {
	
	@Autowired
	private RentalDao rentalDao;

	public boolean rentalBook(Rental rental) {
		return rentalDao.insertRental(rental);
	}
	public boolean returnBook(Rental rental) {
		return rentalDao.patchRental(rental);
	}
	public Object lostBook() {
		return null;
	}
	public boolean updateRentalInfo(Rental rental) {
		return rentalDao.updateRental(rental);
	}
	public List<Rental> getRentalList(String user_id){
		return rentalDao.selectRentals(user_id);
	}
	public List<Rental> getRentalList(int book_id){
		return rentalDao.selectRentals(book_id);
	}
	public Rental getRental(int rental_id){
		return rentalDao.selectRental(rental_id);
	}
}

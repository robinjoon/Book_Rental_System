package dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
public class Rental {
	private int rentalId;
	private String email;
	private int bookId;
	private Timestamp startTime;
	private Timestamp dueTime;
	private boolean returned;
	
	@RequiredArgsConstructor
	public static class Builder{
		
		private int rentalId;
		@NonNull
		private String email;
		private int bookId;
		@NonNull
		private Timestamp startTime;
		@NonNull
		private Timestamp dueTime;
		private boolean returned;
		
		public void setRentalId(int rentalId) {
			this.rentalId = rentalId;
		}
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
		public void setReturned(boolean returned) {
			this.returned = returned;
		}
		
		public Rental build() {
			Rental rental = new Rental();
			rental.bookId = bookId;
			rental.dueTime = dueTime;
			rental.email = email;
			rental.rentalId = rentalId;
			rental.returned = returned;
			rental.startTime = startTime;
			return rental;
		}
		
	}
}

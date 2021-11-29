package dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
public class Rental {
	private int rentalId;
	private String userId;
	private int bookId;
	private Timestamp startTime;
	private Timestamp dueTime;
	private boolean returned;
	
	@RequiredArgsConstructor
	public static class Builder{
		
		private int rentalId;
		@NonNull
		private String userId;
		private int bookId;
		@NonNull
		private Timestamp startTime;
		@NonNull
		private Timestamp dueTime;
		private boolean returned;
		
		public Builder setRentalId(int rentalId) {
			this.rentalId = rentalId;
			return this;
		}
		public Builder setBookId(int bookId) {
			this.bookId = bookId;
			return this;
		}
		public Builder setReturned(boolean returned) {
			this.returned = returned;
			return this;
		}
		
		public Rental build() {
			Rental rental = new Rental();
			rental.bookId = bookId;
			rental.dueTime = dueTime;
			rental.userId = userId;
			rental.rentalId = rentalId;
			rental.returned = returned;
			rental.startTime = startTime;
			return rental;
		}
		
	}
}

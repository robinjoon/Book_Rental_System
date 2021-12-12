package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.Rental;
import services.RentalService;

@RestController
@RequestMapping("/rental")
public class RentalController {
	@Autowired
	private RentalService rentalService;
	
	@PostMapping("")
	private ResponseEntity<?> rentalBook(@RequestBody Rental rental){
		if(rentalService.rentalBook(rental)) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	@PostMapping("/{rentalId}")
	private ResponseEntity<?> returnBook(@PathVariable("{rentalId") int rentalId){
		Rental rental = new Rental.Builder(null, null, null)
				.setRentalId(rentalId)
				.setReturned(true)
				.build();
		if(rentalService.returnBook(rental)) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	@PutMapping("/{rentalId}")
	private ResponseEntity<?> updateDue(@RequestBody Rental rental){
		if(rentalService.updateRentalInfo(rental)) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	@GetMapping("/{userId}")
	private ResponseEntity<List<Rental>> getRentalList(@PathVariable("{userId}")String userId){
		try {
			int bookId = Integer.parseInt(userId);
			return getRentalList(bookId);
		}catch (NumberFormatException e) {
			List<Rental> result = rentalService.getRentalList(userId);
			if(result == null) {
				return ResponseEntity.badRequest().build();
			}else {
				return ResponseEntity.ok(result);
			}
		}
	}
	private ResponseEntity<List<Rental>> getRentalList(@PathVariable("{bookId}")int bookId){
		List<Rental> result = rentalService.getRentalList(bookId);
		if(result == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	@GetMapping("/{rentalId}")
	private ResponseEntity<Rental> getRental(@PathVariable("{rentalId}")int rentalId){
		Rental result = rentalService.getRental(rentalId);
		if(result == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
}

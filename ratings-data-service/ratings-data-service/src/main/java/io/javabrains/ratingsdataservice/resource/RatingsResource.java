package io.javabrains.ratingsdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	@RequestMapping("/{movieID}")
	public Rating getRating(@PathVariable("movieID")String movieID) {
		
		return new Rating(movieID, 4);
		
	}
	
	@RequestMapping("users/{userID}")
	public UserRating getUserRating(@PathVariable("userID") String userID) {
		
		List <Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3)
				);
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}

}

package io.javabrains.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userID}")
	public List<CatalogItem> getCatalog(@PathVariable("userID") String userID) {
	
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userID, UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> {
			//For each movie ID, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieID(), Movie.class);
			//Put them all together
			return new CatalogItem(movie.getName(), movie.getDescription(),  rating.getRating());
		})
		.collect(Collectors.toList());
		
	}
}

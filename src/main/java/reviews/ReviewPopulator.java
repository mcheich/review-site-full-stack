package reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reviews.models.Category;
import reviews.models.Review;
import reviews.models.Tag;
import reviews.repositories.CategoryRepository;
import reviews.repositories.ReviewRepository;
import reviews.repositories.TagRepository;


@Component
public class ReviewPopulator implements CommandLineRunner {


	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		Category russian = new Category("Books with Russian Authors"); 
		categoryRepo.save(russian);
		Category english = new Category("Books with English Authors"); 
		categoryRepo.save(english);
		Category american = new Category("Books with American Authors"); 
		categoryRepo.save(american);
		
		Tag childAsMain = new Tag("Child as Main Character"); 
		tagRepo.save(childAsMain);
		Tag bearded = new Tag("Bearded Author");
		tagRepo.save(bearded);
		Tag historical = new Tag("Historical Fiction");
		tagRepo.save(historical);
		Tag journey = new Tag("Jounery");
		tagRepo.save(journey);
		Tag comingOfAge = new Tag("Coming of Age Story");
		tagRepo.save(comingOfAge);
		Tag characters = new Tag("Lots of Characters");
		tagRepo.save(characters);
	
		reviewRepo.save(new Review("War and Peace", "War and Peace Description Here", russian, bearded, characters, historical));
		reviewRepo.save(new Review("Crime and Punishment", "Crime and Punishment Description Here", russian, bearded));
		reviewRepo.save(new Review("The Idiot", "The Idiot Description Here", russian, bearded, characters));
		reviewRepo.save(new Review("Vanity Fair", "Vanity Fair Description Here", english, journey));
		reviewRepo.save(new Review("David Copperfield", "David Copperfield Description Here", english, childAsMain, bearded, journey, comingOfAge));
		reviewRepo.save(new Review("The Advetures of Tom Sawyer", "The Advetures of Tom Sawyer Description Here", american, childAsMain, bearded, journey, comingOfAge, historical));
		
	}
}

package reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reviews.models.Category;
import reviews.models.Comment;
import reviews.models.Review;
import reviews.models.Tag;
import reviews.repositories.CategoryRepository;
import reviews.repositories.CommentRepository;
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

	@Resource
	private CommentRepository commentRepo;

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

		Review warAndPeace = new Review("War and Peace", "War and Peace Description Here", russian, bearded, characters,
				historical);
		reviewRepo.save(warAndPeace);
		Review crimeAndPunish = new Review("Crime and Punishment", "Crime and Punishment Description Here", russian,
				bearded);
		reviewRepo.save(crimeAndPunish);
		Review theIdiot = new Review("The Idiot", "The Idiot Description Here", russian, bearded, characters);
		reviewRepo.save(theIdiot);
		Review vaityFair = new Review("Vanity Fair", "Vanity Fair Description Here", english, journey);
		reviewRepo.save(vaityFair);
		Review davidCopper = new Review("David Copperfield", "David Copperfield Description Here", english, childAsMain,
				bearded, journey, comingOfAge); 
		reviewRepo.save(davidCopper);
		Review tomSawyer = new Review("The Advetures of Tom Sawyer", "The Advetures of Tom Sawyer Description Here",
				american, childAsMain, bearded, journey, comingOfAge, historical); 
		reviewRepo.save(tomSawyer);

		commentRepo.save(new Comment("I LOVE war and peace!", warAndPeace));
		commentRepo.save(new Comment("Sooo many characters", warAndPeace));
		commentRepo.save(new Comment("After the first 100 pages, you can't stop", warAndPeace));
		
		commentRepo.save(new Comment("dudes crazy", crimeAndPunish));
		commentRepo.save(new Comment("those russians", crimeAndPunish));
		
		commentRepo.save(new Comment("He's not an Idiot, he's great", theIdiot));
		commentRepo.save(new Comment("such is the world", theIdiot));
		
		commentRepo.save(new Comment("she is so cra-cra!", vaityFair));
		commentRepo.save(new Comment("I knew she did that...you know what...", vaityFair));
		
		commentRepo.save(new Comment("I kind of want to beat up david", davidCopper));
		commentRepo.save(new Comment("The uncle is my fav character", davidCopper));
		
		commentRepo.save(new Comment("This book is so much fun!", tomSawyer));
		commentRepo.save(new Comment("Talk about taking on tough topics", tomSawyer));
		
	}
}

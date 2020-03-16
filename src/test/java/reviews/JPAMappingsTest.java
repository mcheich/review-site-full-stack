package reviews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
//import org.junit.jupiter.api.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reviews.models.Category;
import reviews.models.Comment;
import reviews.models.Review;
import reviews.models.Tag;
import reviews.repositories.CategoryRepository;
import reviews.repositories.CommentRepository;
import reviews.repositories.ReviewRepository;
import reviews.repositories.TagRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private TagRepository tagRepo;

	@Resource
	private CommentRepository commentRepo;

	@Test
	public void shouldSaveAndLoadComments() {
		// Arrange
		Comment comment = new Comment("text");
		commentRepo.save(comment);
		long commentId = comment.getId();

		// Act
		entityManager.flush();
		entityManager.clear();

		Optional<Comment> result = commentRepo.findById(commentId);

		// Assert
		assertThat(result.get().getCommentText(), is("text"));
	}

	@Test
	public void shouldEstablishCommentToReviewRelationship() {
		// Arrange
		Category category = new Category("name");
		categoryRepo.save(category);
		Review review = new Review("name", "description", category);
		reviewRepo.save(review);
		Comment commentOne = new Comment("commentOne", review);
		commentRepo.save(commentOne);
		Comment commentTwo = new Comment("commentTwo", review);
		commentRepo.save(commentTwo);
		
		long reviewId = review.getId();
		
		//Act
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();

		//Assert
		assertThat(review.getComments(), containsInAnyOrder(commentOne, commentTwo));
	}
	
	@Test
	public void shouldFindCommentsForReviewId() {
		Review review = new Review();
		reviewRepo.save(review);
		Comment commentOne = new Comment("commentOne", review);
		commentRepo.save(commentOne);
		Comment commentTwo = new Comment("commentTwo", review);
		commentRepo.save(commentTwo);
		long reviewId = review.getId();
		
		//Act
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		
		//Assert
		assertThat(review.getComments(), containsInAnyOrder(commentOne, commentTwo));
	}

	@Test
	public void shouldSaveAndLoadCategory() {
		// Arrange
		Category category = new Category("name");
		categoryRepo.save(category);
		long categoryId = category.getId();
		// Act
		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();

		// Assert
		assertThat(category.getName(), is("name"));
	}

	@Test
	public void establishCategoryToReviewRelationship() {
		// Arrange
		Category category = new Category("name");
		category = categoryRepo.save(category);
		Review reviewOne = reviewRepo.save(new Review("nameOne", "descriptionOne", category));
		Review reviewTwo = reviewRepo.save(new Review("nameTwo", "descriptionTwo", category));
		
		long categoryId = category.getId();

		// Act
		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();

		// Assert
		assertThat(category.getReviews(), containsInAnyOrder(reviewOne, reviewTwo));
	}

	@Test
	public void shouldSaveAndLoadReview() {
		// Arrange
		Category category = categoryRepo.save(new Category("name"));
		Review review = reviewRepo.save(new Review("name", "description", category));
		long reviewId = review.getId();

		// Act
		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();

		// Assert
		assertThat(review.getName(), is("name"));
	}

	@Test
	public void shouldGenerateReviewId() {
		// Arrange
		Category category = categoryRepo.save(new Category("name"));
		Review review = reviewRepo.save(new Review("name", "description", category));
		long reviewId = review.getId();
		// Act
		entityManager.flush();
		entityManager.clear();

		// Assert
		assertThat(reviewId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadTag() {
		// Arrange
		Category category = categoryRepo.save(new Category("name"));
		Review reviewOne = reviewRepo.save(new Review("nameOne", "descriptionOne", category));
		Review reviewTwo = reviewRepo.save(new Review("nameTwo", "descriptionTwo", category));
		Tag tag = tagRepo.save(new Tag("tag"));
		long tagId = tag.getId();

		// Act
		entityManager.flush();
		entityManager.clear();

		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();

		// Assert
		assertThat(tag.getName(), is("tag"));
	}

	@Test
	public void shouldGenerateTagId() {
		// Arrange
		Category category = categoryRepo.save(new Category("name"));
		Review reviewOne = reviewRepo.save(new Review("nameOne", "descriptionOne", category));
		Review reviewTwo = reviewRepo.save(new Review("nameTwo", "descriptionTwo", category));
		Tag tag = tagRepo.save(new Tag("tag"));
		long tagId = tag.getId();

		// Act
		entityManager.flush();
		entityManager.clear();

		// Assert
		assertThat(tagId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishReveiwToTagRelationship() {
		// Arrange
		Category category = categoryRepo.save(new Category("name"));
		Tag tagOne = tagRepo.save(new Tag("tagOne"));
		Tag tagTwo = tagRepo.save(new Tag("tagTwo"));
		Review review = reviewRepo.save(new Review("nameOne", "descriptionOne", category, tagOne, tagTwo));
		long reviewId = review.getId();

		// Act
		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();

		// Assert
		assertThat(review.getTags(), containsInAnyOrder(tagOne, tagTwo));
	}

	@Test
	public void shouldFindACategoryForReviewContains() {
		// Arrange
		Category category = categoryRepo.save(new Category("categoryName"));
		Review review = reviewRepo.save(new Review("reviewName", "description", category));

		// Act
		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findByReviewsContains(review);
		category = result.get();

		// Assert
		assertThat(category.getName(), is("categoryName"));
	}

	@Test
	public void shouldFindAReviewForCategoryId() {
		// Arrange
		Category category = categoryRepo.save(new Category("categoryName"));
		long categoryId = category.getId();
		Review review = reviewRepo.save(new Review("reviewName", "description", category));

		// Act
		entityManager.flush();
		entityManager.clear();

		Collection<Review> result = reviewRepo.findByCategoryId(categoryId);
		// review = result.get();

		// Assert
		assertThat(result, contains(review));
	}

	@Test
	public void shouldFindAllReviewsForCategoryId() {
		// Arrange
		Category category = categoryRepo.save(new Category("categoryName"));
		Review reviewTwo = reviewRepo.save(new Review("reviewOne", "descriptionOne", category));
		Review reviewOne = reviewRepo.save(new Review("reviewTwo", "descriptionTwo", category));
		long categoryId = category.getId();

		// Act
		entityManager.flush();
		entityManager.clear();

		Collection<Review> result = reviewRepo.findByCategoryId(categoryId);

		// Assert
		assertThat(result, containsInAnyOrder(reviewOne, reviewTwo));
	}

}

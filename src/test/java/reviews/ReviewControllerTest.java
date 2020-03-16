package reviews;


import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import reviews.ReviewNotFoundException;
import reviews.controllers.ReviewController;
import reviews.models.Category;
import reviews.models.Comment;
import reviews.models.Review;
import reviews.models.Tag;
import reviews.repositories.CategoryRepository;
import reviews.repositories.CommentRepository;
import reviews.repositories.ReviewRepository;
import reviews.repositories.TagRepository;

public class ReviewControllerTest {

	@Mock
	private Model model;

	/*
	 * Mikes Note to self: Ask about this annotation in class. Why InjectMocks vs
	 * just a Mock?
	 **/

	@InjectMocks
	private ReviewController underTest;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private Review reviewOne;

	@Mock
	private Review reviewTwo;

	@Mock
	private CategoryRepository categoryRepo;

	@Mock
	private Category categoryOne;

	@Mock
	private Category categoryTwo;

	@Mock
	private TagRepository tagRepo;

	@Mock
	private Tag tagOne;

	@Mock
	private Tag tagTwo;
	
	@Mock
	private CommentRepository commentRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		// Arrange
		long id = 1;
		when(reviewRepo.findById(id)).thenReturn(Optional.of(reviewOne));

		// Act
		underTest.findOneReview(id, model);

		// Assert
		verify(model).addAttribute("review", reviewOne);
	}

	@Test
	public void shouldAddAllReviewsToModel() {
		// Arrange
		Collection<Review> allReviews = Arrays.asList(reviewOne, reviewTwo);
		when(reviewRepo.findAll()).thenReturn(allReviews);

		// Act
		underTest.findAllReviews(model);

		// Assert
		verify(model).addAttribute("reviews", allReviews);
	}

	@Test
	public void shouldAddAllReviewsOfCategoryToModel() {
		// Arrange
		Collection<Review> allReviews = Arrays.asList(reviewOne, reviewTwo);
		long id = 1;
		when(reviewRepo.findByCategoryId(id)).thenReturn(allReviews);
		
		// Act
		underTest.findAllReviewsOfCategory(id, model);
		
		// Assert
		verify(model).addAttribute("category", allReviews);
	}


	@Test
	public void shouldAddSingleCategoryToModel() {
		// Arrange
		long id = 1;
		when(categoryRepo.findById(id)).thenReturn(Optional.of(categoryOne));

		// Act
		underTest.findOneCategory(id, model);

		// Assert
		verify(model).addAttribute("category", categoryOne);
	}

	@Test
	public void shouldAddAllCategoriesToModel() {
		// Arrange
		Collection<Category> allCategories = Arrays.asList(categoryOne, categoryTwo);
		when(categoryRepo.findAll()).thenReturn(allCategories);

		// Act
		underTest.findAllCategories(model);

		// Assert
		verify(model).addAttribute("categories", allCategories);
	}

	@Test
	public void shouldAddSingleTagToModel() {
		// Arrange
		long id = 1;
		when(tagRepo.findById(id)).thenReturn(Optional.of(tagOne));

		// Act
		underTest.findOneTag(id, model);

		// Assert
		verify(model).addAttribute("tag", tagOne);
	}

	@Test
	public void shouldAddAllTagsToModel() {
		// Arrange
		Collection<Tag> allTags = Arrays.asList(tagOne, tagTwo);
		when(tagRepo.findAll()).thenReturn(allTags);

		// Act
		underTest.findAllTags(model);

		// Assert
		verify(model).addAttribute("tags", allTags);
	}
	
	@Test
	public void shouldAddAllTaggedReviewsToModel() {
		//Arrange
		String tagName = tagOne.getName();
		Review review1 = new Review("name1","description1", categoryOne, tagOne);
		Review review2 = new Review("name2","description2", categoryTwo, tagOne);
		Collection<Review> taggedReviews = Arrays.asList(review1, review2);
		when(tagRepo.findByName(tagName)).thenReturn(tagOne);
		when(reviewRepo.findByTagsContains(tagOne)).thenReturn(taggedReviews);
		
		//Act
		underTest.findAllReviewsOfTag(tagName, model);

		System.out.println(reviewRepo.findByTagsContains(tagOne));
		
		
		//Assert
		verify(model).addAttribute("reviews", taggedReviews);
	}
	
	@Test
	public void shouldAddCommentToReview() {
		//Arrange
		String commentText = "comment test";
		long reviewId = reviewOne.getId();
		
		//Act
		underTest.addCommentsToReview(reviewId, commentText);
		ArgumentCaptor<Comment> commentArgument = ArgumentCaptor.forClass(Comment.class);
		
		//Assert
		verify(commentRepo).save(commentArgument.capture());
		assertEquals("comment test", commentArgument.getValue().getCommentText());

	}

}

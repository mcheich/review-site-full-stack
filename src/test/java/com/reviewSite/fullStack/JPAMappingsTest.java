package com.reviewSite.fullStack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private TagRepository tagRepo;

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
		//Arrange
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
		
		//Assert
		assertThat(tag.getName(), is("tag"));
	}
	
	@Test
	public void shouldGenerateTagId() {
		//Arrange
		Category category = categoryRepo.save(new Category("name"));
		Review reviewOne = reviewRepo.save(new Review("nameOne", "descriptionOne", category));
		Review reviewTwo = reviewRepo.save(new Review("nameTwo", "descriptionTwo", category));
		Tag tag = tagRepo.save(new Tag("tag"));
		long tagId = tag.getId();

		// Act
		entityManager.flush();
		entityManager.clear();
		
		//Assert
		assertThat(tagId, is(greaterThan(0L)));		
	}
	
	@Test
	public void shouldEstablishReveiwToTagRelationship() {
		//Arrange
		Category category = categoryRepo.save(new Category("name"));
		Tag tagOne = tagRepo.save(new Tag("tagOne"));
		Tag tagTwo = tagRepo.save(new Tag("tagTwo"));
		Review review = reviewRepo.save(new Review("nameOne", "descriptionOne", category, tagOne, tagTwo));
		long reviewId = review.getId();
		
		//Act
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		
		//Assert
		assertThat(review.getTags(), containsInAnyOrder(tagOne, tagTwo));
	}
	
	/* Cannot figure out how to make this test work*/
	@Test
	public void shouldFindCategoriesForReviewContains() {
		//Arrange
		Category category = categoryRepo.save(new Category("categoryName")); 
		Review review = reviewRepo.save(new Review("reviewName", "description", category));
		
		
		//Act
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findByReviewsContains(review);
 		category = result.get();
		
		//Assert
		assertThat(category.getName(), is("categoryName"));
	}
	
}

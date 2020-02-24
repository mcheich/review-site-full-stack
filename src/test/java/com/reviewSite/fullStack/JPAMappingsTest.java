package com.reviewSite.fullStack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
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

}

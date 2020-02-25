package com.reviewSite.fullStack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerMockMvcTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;

	@MockBean
	private CategoryRepository categoryRepo;

	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Review reviewOne;
	

	@Test
	public void shouldGetStatusOfOkWhenWhenNavigatingToAllReviewsOfCategory() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(reviewOne));
		mvc.perform(get("/categories?id=1")).andExpect(status().isOk())
				.andExpect(view().name("category"));
	}
	
	
	@Test
	public void shouldAddSingleReviewToTheModel() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(reviewOne));
		mvc.perform(get("/categories?id=1")).andExpect(model().attribute("review", is(reviewOne)));
	}
	
	@Test
	public void shouldAllReviewsOfACategoryToTheModel() throws Exception {
		//when(reviewRepo.findByCategoryId(categoryId))
	}

}

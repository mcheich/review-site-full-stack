package reviews;


import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import reviews.controllers.ReviewController;
import reviews.models.Category;
import reviews.models.Comment;
import reviews.models.Review;
import reviews.repositories.CategoryRepository;
import reviews.repositories.CommentRepository;
import reviews.repositories.ReviewRepository;
import reviews.repositories.TagRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;

	@MockBean
	private CategoryRepository categoryRepo;

	@MockBean
	private TagRepository tagRepo;
	
	@MockBean
	private CommentRepository commentRepo;
	
	@Mock
	private Review reviewOne;

	@Mock
	private Review reviewTwo;

	@Mock
	private Category categoryOne;
	
	@Mock
	private Category categoryTwo;

	@Mock
	private Comment commentOne;

	@Mock
	private Comment commentTwo;
	
	@Test
	public void shouldRouteToAllReviewsOfACategory() throws Exception {
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}
	
	@Test
	public void shouldAddAllReviewsOfACategoryToTheModel() throws Exception {
		Collection<Review> reviews = Arrays.asList(reviewOne, reviewTwo);
		when(reviewRepo.findByCategoryId(1L)).thenReturn(reviews);
		mvc.perform(get("/category?id=1")).andExpect(model().attribute("category", is(reviews)));
	}
	
	@Test
	public void shouldRouteToAllCategories() throws Exception {
		mvc.perform(get("/categories")).andExpect(view().name(is("categories")));
	}

	@Test
	public void shouldAddCategoriesToTheModel() throws Exception {
		Collection<Category> allCategories = Arrays.asList(categoryOne, categoryTwo);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/categories")).andExpect(model().attribute("categories", allCategories));
	}
	
	
}

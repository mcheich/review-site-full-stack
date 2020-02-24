package com.reviewSite.fullStack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;

	public void findOneReview(long id, Model model) {

		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("review", review.get());
		}

	}

	public void findAllReviews(Model model) {

		model.addAttribute("reviews", reviewRepo.findAll());

	}

	public void findOneCategory(long id, Model model) {

		Optional<Category> category = categoryRepo.findById(id);

		if (category.isPresent()) {
			model.addAttribute("category", category.get());
		}

	}

	public void findAllCategories(Model model) {

		model.addAttribute("categories", categoryRepo.findAll());

	}

	public void findOneTag(long id, Model model) {
		
		Optional<Tag> tag = tagRepo.findById(id);

		if (tag.isPresent()) {
			model.addAttribute("tag", tag.get());
		}
		
	}

	public void findAllTags(Model model) {
		
		model.addAttribute("tags", tagRepo.findAll());
		
	}

}

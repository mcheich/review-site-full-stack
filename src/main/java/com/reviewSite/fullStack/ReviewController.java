package com.reviewSite.fullStack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;

	@RequestMapping("/categories")
	public String findOneReview(@RequestParam(value="id") long id, Model model) throws ReviewNotFoundException {

		Optional<Review> review = reviewRepo.findById(id);
		

		if (review.isPresent()) {
			model.addAttribute("review", review.get());
			return "category";
		}
		
		throw new ReviewNotFoundException();

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

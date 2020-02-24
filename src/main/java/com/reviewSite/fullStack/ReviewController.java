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

	
	public void findOneReview(long id, Model model) {
		
		Optional<Review> review = reviewRepo.findById(id);
		
		if(review.isPresent()) {
			model.addAttribute("review", review.get());
		}
		
	}


	public void findAllReviews(Model model) {
		
		model.addAttribute("reviews", reviewRepo.findAll());
		
	}

}

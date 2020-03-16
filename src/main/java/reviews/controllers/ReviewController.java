package reviews.controllers;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import reviews.ReviewNotFoundException;
import reviews.models.Category;
import reviews.models.Comment;
import reviews.models.Review;
import reviews.models.Tag;
import reviews.repositories.CategoryRepository;
import reviews.repositories.CommentRepository;
import reviews.repositories.ReviewRepository;
import reviews.repositories.TagRepository;

@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;

	@Resource
	private CommentRepository commentRepo;

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id") long id, Model model) throws ReviewNotFoundException {

		Optional<Review> review = reviewRepo.findById(id);
		Collection<Comment> comments = review.get().getComments();

		if (review.isPresent()) {
			model.addAttribute("review", review.get());
			model.addAttribute("comments", comments);
			return "review";
		}

		throw new ReviewNotFoundException();

	}

	@RequestMapping("/category")
	public String findAllReviewsOfCategory(@RequestParam(value = "id") long id, Model model) {

		model.addAttribute("category", reviewRepo.findByCategoryId(id));
		return "category";

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

	@RequestMapping("/categories")
	public String findAllCategories(Model model) {

		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";

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

	@RequestMapping("/tags")
	public String findAllReviewsOfTag(@RequestParam(value = "tagName") String tagName, Model model) {
		Tag tag = tagRepo.findByName(tagName);
		model.addAttribute("reviews", reviewRepo.findByTagsContains(tag));
		return "tags";
	}

	@RequestMapping(path = "/remove-tag/{tagId}/{reviewId}", method = RequestMethod.POST)
	public String removeTagFromReview(@PathVariable long tagId, @PathVariable long reviewId, Model model) {

		Tag tagToRemove = tagRepo.findById(tagId).get();
		Review review = reviewRepo.findById(reviewId).get();

		if (tagToRemove != null) {
			review.removeTag(tagToRemove);
			reviewRepo.save(review);
		}

		model.addAttribute("review", review);

		return "partials/tag-list-removed";
	}

	@RequestMapping("/add-comment")
	public String addCommentsToReview(@RequestParam(value = "Id") long Id, String commentText) {

		Optional<Review> review = reviewRepo.findById(Id);
		Comment comment = new Comment(commentText, review.get());
		commentRepo.save(comment);
		
		return "redirect:/review?id=" + Id;

	}

}

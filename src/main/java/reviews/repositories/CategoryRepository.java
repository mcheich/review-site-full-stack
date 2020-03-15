package reviews.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import reviews.models.Category;
import reviews.models.Review;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByReviewsContains(Review review);

	Collection<Review> findByReviewsId(long categoryId);

}

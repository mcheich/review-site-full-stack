package reviews.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import reviews.models.Review;
import reviews.models.Tag;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategoryId(long categoryId);

	Collection<Review> findByTagsContains(Tag tag);

}

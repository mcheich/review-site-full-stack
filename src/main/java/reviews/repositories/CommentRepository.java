package reviews.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import reviews.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Collection<Comment> findByReviewId(long reviewId);

}

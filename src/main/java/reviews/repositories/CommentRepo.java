package reviews.repositories;

import org.springframework.data.repository.CrudRepository;

import reviews.models.Comment;

public interface CommentRepo extends CrudRepository<Comment, Long> {

}

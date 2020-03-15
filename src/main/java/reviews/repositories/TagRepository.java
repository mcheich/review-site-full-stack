package reviews.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import reviews.models.Tag;


@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByName(String tagName);

}

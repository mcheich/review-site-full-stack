package com.reviewSite.fullStack;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategoryId(long categoryId);

	Collection<Review> findByTagsContains(Tag tag);

}

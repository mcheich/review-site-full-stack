package com.reviewSite.fullStack;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByReviewsContains(Review review);

	Collection<Review> findByReviewsId(long categoryId);

}

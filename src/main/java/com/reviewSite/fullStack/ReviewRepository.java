package com.reviewSite.fullStack;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

	Optional<Review> findByCategoryId(long categoryId);

}

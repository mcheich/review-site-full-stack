package com.reviewSite.fullStack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByName(String tagName);

}

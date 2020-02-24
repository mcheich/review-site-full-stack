package com.reviewSite.fullStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	/************* Field Values ****************/
	@Id
	@GeneratedValue
	private long id;

	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;

	public long getId() {
		return this.id;
	}

	/************* Getters ****************/
	public Object getName() {
		return this.name;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	/************* Constructors ****************/
	public Category() {

	}

	public Category(String name, Review... reviews) {
		this.name = name;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
	}

	/************* Overrides ****************/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

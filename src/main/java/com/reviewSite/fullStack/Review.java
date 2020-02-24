package com.reviewSite.fullStack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne
	private Category category;

	private String name;

	private String description;
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public Review() {
		
	}
	
	public Review(String name, String description, Category category) {
		this.name = name;
		this.description = description;
		this.category = category;
	}

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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

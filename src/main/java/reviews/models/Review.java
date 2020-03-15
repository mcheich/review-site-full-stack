package reviews.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	/************* Field Values ****************/
	@Id
	@GeneratedValue
	long id;

	@ManyToOne
	private Category category;

	private String name;

	@Lob
	private String description;

	@ManyToMany
	private Collection<Tag> tags;
	
	@OneToMany
	private Collection<Comment> comments;

	/************* Getters ****************/

	public Category getCategory() {
		return category;
	}

	public Collection<Tag> getTags() {
		return this.tags;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Collection<Comment> getComments() {
		return this.comments;
	}

	/************* Constructors ****************/
	public Review() {

	}

	public Review(String name, String description, Category category) {
		this.name = name;
		this.description = description;
		this.category = category;
	}

	public Review(String name, String description, Category category, Tag... tags) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}

	public Review(String name, String description, Category category, Comment... comments) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.comments = new HashSet<>(Arrays.asList(comments));
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/************* General ****************/

	public void removeTag(Tag tagToRemove) {
		tags.remove(tagToRemove);
	}

}
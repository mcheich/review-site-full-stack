package reviews.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	/************* Field Values ****************/
	@Id
	@GeneratedValue
	long Id;
	
	@ManyToOne
	private Review review;
	
	private String commentText;


	public long getId() {
		return Id;
	}

	public Object getCommentText() {
		return commentText;
	}

	/************* Constructors ****************/
	public Comment() {
		
	}
	
	public Comment(String commentText) {
		this.commentText = commentText;
	}

	/************* Overrides ****************/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
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
		Comment other = (Comment) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	
}

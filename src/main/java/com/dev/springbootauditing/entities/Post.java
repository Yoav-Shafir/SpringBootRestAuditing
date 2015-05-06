package com.dev.springbootauditing.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="POST")	
public class Post extends AbstractEntity {
	
	@Version
	public Integer version;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="POST_DATE")
	private Date postDate;

	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	public List<Comment> getComments() {
		return comments;
	}

	public Date getPostDate() {
		return postDate;
	}

	public String getTitle() {
		return title;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Post [postId=" + id + ", title=" + title + ", postDate="
				+ postDate + ", comments=" + comments + "]";
	}

}

package com.dev.springbootauditing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="COMMENT")
public class Comment extends AbstractEntity {
	
	@Version
	public Integer version;

	@Column(name="AUTHOR")
	private String author;

	@Column(name="BODY")
	private String body;
	
	@ManyToOne
	@JoinColumn(name="POST_ID")
	private Post post;
	
	public String getAuthor() {
		return author;
	}

	public String getBody() {
		return body;
	}

	public Post getPost() {
		return post;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}

}

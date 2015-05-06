package com.dev.springbootauditing.other;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity(ExampleListener.class)
public class ExampleRevEntity {
	
	@Id
	@GeneratedValue
	@RevisionNumber
	@Column(name="REV")
	public int id;
	
	@RevisionTimestamp
	@Column(name="REVTSTMP")
	public long timestamp;
	
	@Column(name="USER_ID")
	public Long userId;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

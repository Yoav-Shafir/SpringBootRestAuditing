package com.dev.springbootauditing.other;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Component;

@Component
public class ExampleListener implements RevisionListener{

	@Override
	public void newRevision(Object revisionEntity) {
		ExampleRevEntity exampleRevEntity = (ExampleRevEntity) revisionEntity;
		exampleRevEntity.setUserId(1L);
	}

}

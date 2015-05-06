package com.dev.springbootauditing;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dev.springbootauditing.entities.Comment;
import com.dev.springbootauditing.entities.Post;
import com.dev.springbootauditing.repositories.CommentRepository;
import com.dev.springbootauditing.repositories.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootAuditingApplication.class)
@WebAppConfiguration
public class SpringBootAuditingApplicationTests {
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	PostRepository postRepository;

	@Test
	public void saveComment() {
		Comment comment = new Comment();
		comment.setAuthor("Author");
		comment.setBody("Body");
		commentRepository.save(comment);
	}
	
	@Test
	public void savePostWithComments() {
		// creating new Post.
		Post post1 = new Post();
		post1.setPostDate(new Date());
		post1.setTitle("Title1");
		
		// creating comments.
		Comment comment1 = new Comment();
		comment1.setAuthor("Author1");
		comment1.setBody("Body1");
		// adding the owner post to the current comment.
		comment1.setPost(post1);
		
		Comment comment2 = new Comment();
		comment2.setAuthor("Author2");
		comment2.setBody("Body2");
		// adding the owner post to the current comment.
		comment2.setPost(post1);
		
		// adding the comments to the owner post.
		post1.getComments().add(comment1);
		post1.getComments().add(comment2);
		
		// persist post.
		Post persistedpost1 = postRepository.save(post1);
		
		
		Comment comment3 = new Comment();
		comment3.setAuthor("Author3");
		comment3.setBody("Body3");
		// adding the owner post to the current comment.
		comment3.setPost(persistedpost1);
		
		Comment comment4 = new Comment();
		comment4.setAuthor("Author4");
		comment4.setBody("Body4");
		// adding the owner post to the current comment.
		comment4.setPost(persistedpost1);
		
		// persist post.
		Post persistedpost2 = postRepository.save(persistedpost1);
		
		System.out.println("==================================================");
		Revision<Integer, Post> revision = postRepository.findLastChangeRevision(persistedpost2.id);
		System.out.println("========== revision number : " + revision.getMetadata().getRevisionNumber() + " ==========");
		System.out.println("========== revision date : " + revision.getMetadata().getRevisionDate() + " ==========");
		System.out.println("========== entity title : " + revision.getEntity().getTitle() + " ==========");
		
		
		System.out.println("==================================================");
		Page<Revision<Integer, Post>> revisions = postRepository.findRevisions(persistedpost2.id, new PageRequest(0, 10));
		System.out.println("revisions iteration - start ==================================================");
		revisions.forEach(r -> {
			System.out.println("========== revision number : " + r.getRevisionNumber() + " ==========");
		});
		System.out.println("revisions iteration - end ==================================================");
		
		System.out.println("==================================================");
		Revisions<Integer, Post> wrapper = new Revisions<Integer, Post>(revisions.getContent());
		System.out.println("==================== wrapper.getLatestRevision(): " + wrapper.getLatestRevision().getRevisionNumber());
	}

}

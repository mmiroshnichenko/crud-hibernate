package org.crud.service;

import org.crud.model.Label;
import org.crud.model.Post;
import org.crud.model.PostStatus;
import org.crud.model.Writer;
import org.crud.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PostServiceTest {
    private PostRepository mockPostRepository = Mockito.mock(PostRepository.class);
    private PostService postService = new PostService(mockPostRepository);
    private List<Label> labels;
    private Date date = new Date();
    private Writer writer;

    @Before
    public void setupLabelsAndWriter() {
        labels = new ArrayList<>();
        labels.add(new Label(1L, "news"));
        labels.add(new Label(2L, "test1"));
        labels.add(new Label(3L, "test3"));

        writer = new Writer();
        writer.setId(1L);
        writer.setFirstName("Mykhaylo");
        writer.setLastName("Myroshnychenko");
    }

    private Post getPost() {
        return new Post(
                1L,
                writer,
                "Test saved content",
                date,
                date,
                labels,
                PostStatus.UNDER_REVIEW
        );
    }

    @Test
    public void shouldSavePostTest() {

        Post newPost = new Post();
        newPost.setWriter(writer);
        newPost.setContent("Test saved content");
        newPost.setCreated(date);
        newPost.setUpdated(date);
        newPost.setLabels(labels);
        newPost.setStatus(PostStatus.UNDER_REVIEW);

        Mockito.when(mockPostRepository.save(newPost)).thenReturn(getPost());

        Post savedMockPost = postService.save(newPost);
        assertEquals(getPost(), savedMockPost);
    }

    @Test
    public void shouldUpdatePostTest() {
        Post updatedPost = new Post(
                1L,
                writer,
                "Test saved content",
                date,
                date,
                labels,
                PostStatus.ACTIVE
        );

        Mockito.when(mockPostRepository.update(updatedPost)).thenReturn(updatedPost);

        Post updatedMockPost = postService.updateStatus(updatedPost, PostStatus.ACTIVE);
        assertEquals(updatedPost, updatedMockPost);
    }
}

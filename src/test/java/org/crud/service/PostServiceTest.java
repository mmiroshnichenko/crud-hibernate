package org.crud.service;

import org.crud.model.Label;
import org.crud.model.Post;
import org.crud.model.PostStatus;
import org.crud.model.Writer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PostServiceTest {
    private PostService mockPostService = Mockito.mock(PostService.class);
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

    @Test
    public void shouldSavePostTest() {
        Post savedPost = new Post(
                1L,
                writer,
                "Test saved content",
                date,
                date,
                labels,
                PostStatus.UNDER_REVIEW
        );

        Mockito.when(mockPostService.save("Test saved content", writer, labels)).thenReturn(
            new Post(
                1L,
                writer,
                "Test saved content",
                date,
                date,
                labels,
                PostStatus.UNDER_REVIEW
            )
        );

        Post savedMockPost = mockPostService.save("Test saved content", writer, labels);
        assertEquals(savedPost, savedMockPost);
    }

    @Test
    public void shouldUpdatePostTest() {
        Post post = new Post(
                1L,
                writer,
                "Test saved content",
                date,
                date,
                labels,
                PostStatus.UNDER_REVIEW
        );

        Post updatedPost = new Post(
                1L,
                writer,
                "Test saved content",
                date,
                date,
                labels,
                PostStatus.ACTIVE
        );

        Mockito.when(mockPostService.updateStatus(post, PostStatus.ACTIVE)).thenReturn(
            new Post(
                1L,
                writer,
                "Test saved content",
                date,
                date,
                labels,
                PostStatus.ACTIVE
            )
        );

        Post updatedMockPost = mockPostService.updateStatus(post, PostStatus.ACTIVE);
        assertEquals(updatedPost, updatedMockPost);
    }
}

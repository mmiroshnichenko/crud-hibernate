package org.crud.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PostTest {
    Post post = new Post();

    @Before
    public void setUp() {
        Writer writer = new Writer();
        writer.setId(3L);
        writer.setLastName("Mykhaylo");
        writer.setLastName("Myroshnychenko");

        List<Label> labels = new ArrayList<>();
        Label label = new Label();
        label.setId(2L);
        label.setName("First test label");
        labels.add(label);
        Label label2 = new Label();
        label2.setId(3L);
        label2.setName("Second test label");
        labels.add(label2);

        post.setId(4L);
        post.setWriter(writer);
        post.setCreated(new Date());
        post.setUpdated(new Date());
        post.setStatus(PostStatus.UNDER_REVIEW);
        post.setLabels(labels);
        post.setContent("Test content");
    }

    @Test
    public void shouldCreatePostInstanceTest() {
        assertEquals((Long) 4L, post.getId());
        assertEquals((Long) 3L, post.getWriter().getId());
        assertEquals(PostStatus.UNDER_REVIEW, post.getStatus());
        assertEquals(2, post.getLabels().size());
        assertEquals("Test content", post.getContent());
    }
}

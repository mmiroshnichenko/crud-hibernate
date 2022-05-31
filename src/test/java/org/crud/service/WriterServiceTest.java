package org.crud.service;

import org.crud.model.Post;
import org.crud.model.PostStatus;
import org.crud.model.Writer;
import org.crud.repository.PostRepository;
import org.crud.repository.WriterRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class WriterServiceTest {
    private WriterRepository mockWriterRepository = Mockito.mock(WriterRepository.class);
    private PostRepository mockPostRepository = Mockito.mock(PostRepository.class);
    private WriterService writerService = new WriterService(mockWriterRepository, mockPostRepository);

    private Writer getWriter() {
        return new Writer(1L, "Mykhaylo", "Myroshnychenko");
    }

    private Writer getWriterWithPosts() {
        Writer writer = getWriter();
        writer.addPost(getPost(writer));

        return writer;
    }

    private Post getPost(Writer writer) {
        return new Post(
                1L,
                writer,
                "Test saved content",
                null,
                null,
                null,
                PostStatus.UNDER_REVIEW
        );
    }

    @Test
    public void shouldSaveWriterTest() {
        Writer newWriter = new Writer();
        newWriter.setFirstName("Mykhaylo");
        newWriter.setLastName("Myroshnychenko");

        Mockito.when(mockWriterRepository.save(newWriter)).thenReturn(getWriter());
        Writer savedMockWriter = writerService.save(newWriter);

        assertEquals(savedMockWriter, getWriter());
    }

    @Test
    public void shouldUpdateWriterTest() {
        Writer updatedWriter = new Writer(1L, "Pavel", "Petrenko");

        Mockito.when(mockWriterRepository.update(updatedWriter)).thenReturn(updatedWriter);
        Writer updatedMockWriter = writerService.update(getWriter(), "Pavel", "Petrenko");

        assertEquals(updatedWriter, updatedMockWriter);
    }

    @Test
    public void  shouldGetWriterByIdTest() {
        List<Post> posts = getWriterWithPosts().getPosts();

        Mockito.when(mockWriterRepository.getById(1L)).thenReturn(getWriter());
        Mockito.when(mockPostRepository.getAllByWriter(getWriter())).thenReturn(posts);
        Writer mockWriter = writerService.getById(1L);

        assertEquals(getWriterWithPosts(), mockWriter);
    }
}

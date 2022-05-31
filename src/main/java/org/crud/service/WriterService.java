package org.crud.service;

import org.crud.model.Post;
import org.crud.model.Writer;
import org.crud.repository.PostRepository;
import org.crud.repository.WriterRepository;
import org.crud.repository.impl.JdbcPostRepositoryImpl;
import org.crud.repository.impl.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterService {
    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public WriterService() {
        this.writerRepository = new JdbcWriterRepositoryImpl();
        this.postRepository = new JdbcPostRepositoryImpl();
    }

    public WriterService(WriterRepository writerRepository, PostRepository postRepository) {
        this.writerRepository = writerRepository;
        this.postRepository = postRepository;
    }

    public List<Writer> getList() {
        return writerRepository.getAll();
    }

    public Writer saveNewWriter(String firstName, String lastName) {
        Writer writer = new Writer();
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        return this.save(writer);
    }

    public Writer save(Writer writer) {
        return writerRepository.save(writer);
    }

    public Writer getById(Long id) {
        Writer writer = writerRepository.getById(id);
        if (writer != null) {
            List<Post> posts = postRepository.getAllByWriter(writer);
            writer.setPosts(posts);
        }

        return writer;
    }

    public Writer update(Writer writer, String firstName, String lastName) {
        writer.setFirstName(firstName);
        writer.setLastName(lastName);

        return writerRepository.update(writer);
    }

    public void delete(Long id) {
        writerRepository.deleteById(id);
    }
}

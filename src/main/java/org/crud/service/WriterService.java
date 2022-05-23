package org.crud.service;

import org.crud.model.Post;
import org.crud.model.Writer;
import org.crud.repository.PostRepository;
import org.crud.repository.WriterRepository;
import org.crud.repository.impl.MysqlPostRepositoryImpl;
import org.crud.repository.impl.MysqlWriterRepositoryImpl;

import java.util.List;

public class WriterService {
    private final WriterRepository writerRepository = new MysqlWriterRepositoryImpl();
    private final PostRepository postRepository = new MysqlPostRepositoryImpl();

    public List<Writer> getList() {
        return writerRepository.getAll();
    }

    public Writer save(String firstName, String lastName) {
        Writer writer = new Writer();
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writerRepository.save(writer);

        return writer;
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

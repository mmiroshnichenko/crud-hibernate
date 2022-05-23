package org.crud.repository;

import org.crud.model.Post;
import org.crud.model.Writer;

import java.io.IOException;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Post> getAllByWriter(Writer writer);
}


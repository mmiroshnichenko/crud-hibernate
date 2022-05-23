package org.crud.repository;

import org.crud.model.Label;

import java.io.IOException;
import java.util.List;

public interface LabelRepository extends GenericRepository<Label, Long>{
    List<Label> getByPostId(Long postId);

    List<Label> getByIds(List<Long> ids);
}
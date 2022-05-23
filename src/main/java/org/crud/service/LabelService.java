package org.crud.service;

import org.crud.model.Label;
import org.crud.repository.LabelRepository;
import org.crud.repository.impl.MysqlLabelRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class LabelService {
    private final LabelRepository labelRepository = new MysqlLabelRepositoryImpl();

    public List<Label> getList() {
        return labelRepository.getAll();
    }

    public List<Label> getListByIds(List<Long> ids) {
        return labelRepository.getByIds(ids);
    }

    public Label save(String name) {
        Label label = new Label();
        label.setName(name);
        labelRepository.save(label);

        return label;
    }

    public Label getById(Long id)  {
        return labelRepository.getById(id);
    }

    public Label update(Label label, String name) {
        label.setName(name);
        return labelRepository.update(label);
    }

    public void delete(Long id) {
        //TODO check label existing in posts
        labelRepository.deleteById(id);
    }
}

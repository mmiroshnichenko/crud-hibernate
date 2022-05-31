package org.crud.service;

import org.crud.model.Label;
import org.crud.repository.LabelRepository;
import org.crud.repository.impl.JdbcLabelRepositoryImpl;

import java.util.List;

public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService() {
        this.labelRepository = new JdbcLabelRepositoryImpl();
    }

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public List<Label> getList() {
        return labelRepository.getAll();
    }

    public List<Label> getListByIds(List<Long> ids) {
        return labelRepository.getByIds(ids);
    }

    public Label saveNewLabel(String name) {
        Label label = new Label();
        label.setName(name);
        return this.save(label);
    }

    public Label save(Label label) {
        return labelRepository.save(label);
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

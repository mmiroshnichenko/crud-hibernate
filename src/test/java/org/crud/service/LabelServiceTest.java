package org.crud.service;

import org.crud.model.Label;
import org.crud.repository.LabelRepository;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class LabelServiceTest {

    private LabelRepository mockLabelRepository = Mockito.mock(LabelRepository.class);
    private LabelService labelService = new LabelService(mockLabelRepository);

    private Label getLabel() {
        return new Label(1L, "Test label");
    }

    @Test
    public void shouldSaveLabelTest() {
        Label newLabel = new Label();
        newLabel.setName("Test label");

        Mockito.when(mockLabelRepository.save(newLabel)).thenReturn(getLabel());
        Label savedLabel = labelService.save(newLabel);

        assertEquals(getLabel(), savedLabel);
    }

    @Test
    public void shouldUpdateLabelTest() {
        Label updatedLabel = new Label(1L, "Updated label");

        Mockito.when(mockLabelRepository.update(updatedLabel)).thenReturn(updatedLabel);
        Label mockUpdatedLabel = labelService.update(getLabel(), "Updated label");

        assertEquals(updatedLabel, mockUpdatedLabel);
    }

    @Test
    public void shouldGetLabelByIdTest() {
        Mockito.when(mockLabelRepository.getById(1L)).thenReturn(getLabel());
        Label mockLabel = labelService.getById(1L);

        assertEquals(getLabel(), mockLabel);
    }
}

package org.crud.service;

import org.crud.model.Label;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class LabelServiceTest {

    private LabelService mockLabelService = Mockito.mock(LabelService.class);

    @Test
    public void shouldSaveLabelTest() {
        Label label = new Label(1L, "Test label");

        Mockito.when(mockLabelService.save("Test label")).thenReturn(new Label(1L, "Test label"));
        Label mockLabel = mockLabelService.save("Test label");
        assertEquals(label, mockLabel);
    }

    @Test
    public void shouldUpdateLabelTest() {
        Label label = new Label(1L, "Test label");
        Label updatedLabel = new Label(1L, "Updated label");

        Mockito.when(mockLabelService.update(label, "Updated label")).thenReturn(new Label(1L, "Updated label"));
        Label mockUpdatedLabel = mockLabelService.update(label, "Updated label");

        assertEquals(updatedLabel, mockUpdatedLabel);
    }

    @Test
    public void shouldGetLabelByIdTest() {
        Label label = new Label(1L, "Test label");
        Mockito.when(mockLabelService.getById(1l)).thenReturn(new Label(1l, "Test label"));
        Label mockLabel = mockLabelService.getById(1L);

        assertEquals(label, mockLabel);
    }
}

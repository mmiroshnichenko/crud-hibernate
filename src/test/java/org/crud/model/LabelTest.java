package org.crud.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LabelTest {
    Label label = new Label();

    @Before
    public void setUp() {
        label.setId(2L);
        label.setName("Test label");
    }

    @Test
    public void shouldCreateLabelTest() {
        assertEquals((Long) 2L, label.getId());
        assertEquals("Test label", label.getName());
    }
}

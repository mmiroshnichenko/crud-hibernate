package org.crud.service;

import org.crud.model.Writer;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class WriterServiceTest {
    private WriterService mockWriterService = Mockito.mock(WriterService.class);

    @Test
    public void shouldSaveWriterTest() {
        Writer savedWriter = new Writer(1L, "Mykhaylo", "Myroshnychenko");

        Mockito.when(mockWriterService.save("Mykhaylo", "Myroshnychenko")).thenReturn(new Writer(1L, "Mykhaylo", "Myroshnychenko"));
        Writer savedMockWriter = mockWriterService.save("Mykhaylo", "Myroshnychenko");

        assertEquals(savedWriter, savedMockWriter);
    }

    @Test
    public void shouldUpdateWriterTest() {
        Writer writer = new Writer(1L, "Mykhaylo", "Myroshnychenko");
        Writer updatedWriter = new Writer(1L, "Pavel", "Petrenko");

        Mockito.when(mockWriterService.update(writer, "Pavel", "Petrenko")).thenReturn(new Writer(1L, "Pavel", "Petrenko"));
        Writer updatedMockWriter = mockWriterService.update(writer, "Pavel", "Petrenko");

        assertEquals(updatedWriter, updatedMockWriter);
    }

    @Test
    public void  shouldGetWriterByIdTest() {
        Writer writer = new Writer(1L, "Mykhaylo", "Myroshnychenko");
        Mockito.when(mockWriterService.getById(1L)).thenReturn(new Writer(1L, "Mykhaylo", "Myroshnychenko"));
        Writer mockWriter = mockWriterService.getById(1L);

        assertEquals(writer, mockWriter);
    }
}

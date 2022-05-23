package org.crud.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WriterTest {
    Writer writer = new Writer();

    @Before
    public void setUp() {
        writer.setId(3L);
        writer.setFirstName("Mykhaylo");
        writer.setLastName("Myroshnychenko");
    }

    @Test
    public void shouldCreateWriterInstanceTest() {
        assertEquals((Long) 3L, writer.getId());
        assertEquals("Mykhaylo", writer.getFirstName());
        assertEquals("Myroshnychenko", writer.getLastName());
    }
}

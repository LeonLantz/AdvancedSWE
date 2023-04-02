package org.movie.manager.application.Services;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributeTest {
    @Test
    public void shouldFilterVisibleAttributes() {
        Object o = new Object();
        Attribute attribute1 = new Attribute("a1", o, Object.class, null, null, false);
        Attribute attribute2 = new Attribute("a2", o, Object.class, null, null, true);
        assertThat(Attribute.filterVisibleAttributes(List.of(attribute1, attribute2)))
                .hasSize(1)
                .contains(attribute2);
    }

    @Test
    public void shouldExtractAttributeNames() {
        Object o = new Object();
        String attributeNameOne = "AttributeNameOne";
        String attributeNameTwo = "AttributeNameTwo";
        Attribute attribute1 = new Attribute(attributeNameOne, o, Object.class, null, null, false);
        Attribute attribute2 = new Attribute(attributeNameTwo, o, Object.class, null, null, true);
        assertThat(Attribute.extractAttributeNames(List.of(attribute1, attribute2)))
                .hasSize(2)
                .contains(attributeNameOne)
                .contains(attributeNameTwo);
    }
}

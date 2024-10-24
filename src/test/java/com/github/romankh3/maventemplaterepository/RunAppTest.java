package com.github.romankh3.maventemplaterepository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit-level testing for {@link RunApp} object.
 */
public class MavenTemplateRepositoryTest {

    @Test
    public void shouldCreateJavaRepositoryTemplateMain() {
        RunApp main = new RunApp();
        Assertions.assertNotNull(main);
    }

}

package patterns.factory.gui.page;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.factory.gui.button.Button;
import patterns.factory.gui.button.LinuxButton;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.checkbox.LinuxCheckBox;
import patterns.factory.gui.creator.LinuxGUIFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinuxPageTest {

    private LinuxPage linuxPage;

    @BeforeEach
    void setUp() {
        linuxPage = new LinuxPage(new LinuxGUIFactory());
    }

    @Test
    void renderLinuxPage() {
        assertDoesNotThrow(linuxPage::render);
    }

    @Test
    void createLinuxPageButton() {
        Button button = linuxPage.createButton();
        assertNotNull(button);
        assertTrue(button instanceof LinuxButton);
    }

    @Test
    void createLinuxPageCheckbox() {
        CheckBox checkbox = linuxPage.createCheckbox();
        assertNotNull(checkbox);
        assertTrue(checkbox instanceof LinuxCheckBox);
    }
}

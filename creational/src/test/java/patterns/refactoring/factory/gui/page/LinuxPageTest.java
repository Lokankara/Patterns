package patterns.refactoring.factory.gui.page;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.refactoring.factory.gui.button.Button;
import patterns.refactoring.factory.gui.button.LinuxButton;
import patterns.refactoring.factory.gui.checkbox.CheckBox;
import patterns.refactoring.factory.gui.checkbox.LinuxCheckBox;
import patterns.refactoring.factory.gui.creator.LinuxGUIFactory;

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

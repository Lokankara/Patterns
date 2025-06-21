package patterns.factory.page;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.factory.gui.button.Button;
import patterns.factory.gui.button.LinuxButton;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.checkbox.LinuxCheckBox;
import patterns.factory.gui.creator.LinuxGUIFactory;
import patterns.factory.gui.page.LinuxPage;

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
        assertInstanceOf(LinuxButton.class, button);
    }

    @Test
    void createLinuxPageCheckbox() {
        CheckBox checkbox = linuxPage.createCheckbox();
        assertNotNull(checkbox);
        assertInstanceOf(LinuxCheckBox.class, checkbox);
    }
}

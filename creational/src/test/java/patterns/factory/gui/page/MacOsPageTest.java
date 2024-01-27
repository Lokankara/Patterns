package patterns.factory.gui.page;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.factory.gui.button.Button;
import patterns.factory.gui.button.MacOsButton;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.checkbox.MacOsCheckBox;
import patterns.factory.gui.creator.MacOsGUIFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MacOsPageTest {

    private MacOsPage macOsPage;

    @BeforeEach
    void setUp() {
        macOsPage = new MacOsPage(new MacOsGUIFactory());
    }

    @Test
    void renderMacOsPage() {
        assertDoesNotThrow(macOsPage::render);
    }

    @Test
    void createMacOsPageButton() {
        Button button = macOsPage.createButton();
        assertNotNull(button);
        assertTrue(button instanceof MacOsButton);
    }

    @Test
    void createMacOsPageCheckbox() {
        CheckBox checkbox = macOsPage.createCheckbox();
        assertNotNull(checkbox);
        assertTrue(checkbox instanceof MacOsCheckBox);
    }
}

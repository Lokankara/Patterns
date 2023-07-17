package patterns.refactoring.factory.gui.page;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.refactoring.factory.gui.creator.DynamicPageCreation;
import patterns.refactoring.factory.gui.creator.LinuxGUIFactory;
import patterns.refactoring.factory.gui.creator.MacOsGUIFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class DynamicPageCreationTest {

    DynamicPageCreation creation;

    @BeforeEach
    void setUp() {
        creation = new DynamicPageCreation();
    }

    @Test
    void createPageOnMacOS() {
        System.setProperty("os.name", "Mac OS X");
        Page page = creation.createPage();
        assertNotNull(page);
        assertTrue(page instanceof MacOsPage);
    }

    @Test
    void createPageOnLinux() {
        System.setProperty("os.name", "Linux");
        Page page = creation.createPage();
        assertNotNull(page);
        assertTrue(page instanceof LinuxPage);
    }

    @Test
    void createPageOnUnknownOS() {
        System.setProperty("os.name", "Windows");
        Page page = creation.createPage();
        assertNotNull(page);
        assertTrue(page instanceof LinuxPage);
    }

    @Test
    void createAndRenderMacOsPage() {
        String os = "Mac OS X";
        Page page = os.toLowerCase().contains("mac")
                    ? new MacOsPage(new MacOsGUIFactory())
                    : new LinuxPage(new LinuxGUIFactory());
        assertDoesNotThrow(page::render);
        assertNotNull(page.createButton());
        assertNotNull(page.createCheckbox());
    }

    @Test
    void createAndRenderLinuxPage() {
        String os = "Linux";
        Page page = os.toLowerCase().contains("mac")
                    ? new MacOsPage(new MacOsGUIFactory())
                    : new LinuxPage(new LinuxGUIFactory());
        assertDoesNotThrow(page::render);
        assertNotNull(page.createButton());
        assertNotNull(page.createCheckbox());
    }
}
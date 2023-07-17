package patterns.refactoring.factory.gui.creator;

import patterns.refactoring.factory.gui.page.LinuxPage;
import patterns.refactoring.factory.gui.page.MacOsPage;
import patterns.refactoring.factory.gui.page.Page;

public class DynamicPageCreation {

    public Page createPage() {
        return System.getProperty("os.name")
                     .toLowerCase().contains("mac")
               ? new MacOsPage(new MacOsGUIFactory())
               : new LinuxPage(new LinuxGUIFactory());
    }
}

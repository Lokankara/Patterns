package patterns.factory.gui.creator;

import patterns.factory.gui.page.Page;
import patterns.factory.gui.page.LinuxPage;
import patterns.factory.gui.page.MacOsPage;

public class DynamicPageCreation {

    public Page createPage() {
        return System.getProperty("os.name")
                     .toLowerCase().contains("mac")
               ? new MacOsPage(new MacOsGUIFactory())
               : new LinuxPage(new LinuxGUIFactory());
    }
}

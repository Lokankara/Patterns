package patterns.factory.gui.page;

import patterns.factory.gui.button.Button;
import patterns.factory.gui.button.MacOsButton;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.checkbox.MacOsCheckBox;
import patterns.factory.gui.creator.GUIFactory;

public class MacOsPage
        extends Page {

    public MacOsPage(GUIFactory factory) {
        super(factory);
    }

    @Override
    public Button createButton() {
        return new MacOsButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new MacOsCheckBox();
    }
}

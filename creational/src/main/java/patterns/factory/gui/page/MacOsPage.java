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
    protected Button createButton() {
        return new MacOsButton();
    }

    @Override
    protected CheckBox createCheckbox() {
        return new MacOsCheckBox();
    }
}

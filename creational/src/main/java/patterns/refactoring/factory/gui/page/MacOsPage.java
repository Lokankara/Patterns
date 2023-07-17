package patterns.refactoring.factory.gui.page;

import patterns.refactoring.factory.gui.button.Button;
import patterns.refactoring.factory.gui.button.MacOsButton;
import patterns.refactoring.factory.gui.checkbox.CheckBox;
import patterns.refactoring.factory.gui.checkbox.MacOsCheckBox;
import patterns.refactoring.factory.gui.creator.GUIFactory;

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

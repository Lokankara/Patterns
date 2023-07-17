package patterns.refactoring.factory.gui.creator;

import patterns.refactoring.factory.gui.button.Button;
import patterns.refactoring.factory.gui.button.MacOsButton;
import patterns.refactoring.factory.gui.checkbox.CheckBox;
import patterns.refactoring.factory.gui.checkbox.MacOsCheckBox;

public class MacOsGUIFactory
        implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOsButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacOsCheckBox();
    }
}

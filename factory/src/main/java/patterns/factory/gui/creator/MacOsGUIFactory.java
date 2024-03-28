package patterns.factory.gui.creator;

import patterns.factory.gui.button.Button;
import patterns.factory.gui.button.MacOsButton;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.checkbox.MacOsCheckBox;

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

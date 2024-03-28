package patterns.factory.gui.creator;

import patterns.factory.gui.button.Button;
import patterns.factory.gui.checkbox.CheckBox;

public interface GUIFactory {
    Button createButton();

    CheckBox createCheckBox();
}

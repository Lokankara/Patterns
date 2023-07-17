package patterns.refactoring.factory.gui.creator;

import patterns.refactoring.factory.gui.button.Button;
import patterns.refactoring.factory.gui.checkbox.CheckBox;

public interface GUIFactory {
    Button createButton();

    CheckBox createCheckBox();
}

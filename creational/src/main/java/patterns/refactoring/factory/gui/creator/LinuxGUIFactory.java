package patterns.refactoring.factory.gui.creator;

import patterns.refactoring.factory.gui.button.Button;
import patterns.refactoring.factory.gui.button.LinuxButton;
import patterns.refactoring.factory.gui.checkbox.CheckBox;
import patterns.refactoring.factory.gui.checkbox.LinuxCheckBox;

public class LinuxGUIFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new LinuxCheckBox();
    }
}

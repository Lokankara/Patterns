package patterns.factory.gui.creator;

import patterns.factory.gui.button.Button;
import patterns.factory.gui.button.LinuxButton;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.checkbox.LinuxCheckBox;

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

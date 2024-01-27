package patterns.factory.gui.page;

import patterns.factory.gui.button.Button;
import patterns.factory.gui.button.LinuxButton;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.checkbox.LinuxCheckBox;
import patterns.factory.gui.creator.GUIFactory;

public class LinuxPage extends Page {

    public LinuxPage(GUIFactory factory) {
        super(factory);
    }

    @Override
    protected Button createButton() {
        return new LinuxButton();
    }

    @Override
    protected CheckBox createCheckbox() {
        return new LinuxCheckBox();
    }
}

package patterns.refactoring.factory.gui.page;

import patterns.refactoring.factory.gui.button.Button;
import patterns.refactoring.factory.gui.button.LinuxButton;
import patterns.refactoring.factory.gui.checkbox.CheckBox;
import patterns.refactoring.factory.gui.checkbox.LinuxCheckBox;
import patterns.refactoring.factory.gui.creator.GUIFactory;

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

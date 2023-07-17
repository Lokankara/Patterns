package patterns.refactoring.factory.gui.page;

import patterns.refactoring.factory.gui.button.Button;
import patterns.refactoring.factory.gui.checkbox.CheckBox;
import patterns.refactoring.factory.gui.creator.GUIFactory;

public abstract class Page {

    private final GUIFactory factory;

    protected abstract Button createButton();

    protected abstract CheckBox createCheckbox();

    protected Page(GUIFactory factory) {
        this.factory = factory;
    }

    public void render() {
        factory.createButton().draw();
        factory.createCheckBox().draw();
    }
}

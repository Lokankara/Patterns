package patterns.factory.gui.page;

import patterns.factory.gui.button.Button;
import patterns.factory.gui.checkbox.CheckBox;
import patterns.factory.gui.creator.GUIFactory;

public abstract class Page {

    private final GUIFactory factory;

    public abstract Button createButton();

    public abstract CheckBox createCheckbox();

    protected Page(GUIFactory factory) {
        this.factory = factory;
    }

    public void render() {
        factory.createButton().draw();
        factory.createCheckBox().draw();
    }
}

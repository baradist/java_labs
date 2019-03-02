package view;

import model.Educator;
import service.Service;

public class EducatorPanel extends SavePanel {
    private final TitledTextArea nameTextArea = new TitledTextArea("Name");
    private final TitledTextArea degreeTextArea = new TitledTextArea("Degree");

    EducatorPanel(HelloWorldSwing parent, Service service) {
        super(parent, service);
        addTextAreas();
        addButtonsAndStatusBar(parent);
    }

    @Override
    protected void addTextAreas() {
        addTextAreas(nameTextArea, degreeTextArea);
    }

    @Override
    protected void emptyTextAreas() {
        emptyTextAreas(nameTextArea, degreeTextArea);
    }

    @Override
    protected boolean save() {
        if (nameTextArea.getText().isEmpty()) {
            System.out.println("Name is empty");
            return false;
        }
        if (degreeTextArea.getText().isEmpty()) {
            System.out.println("Degree is empty");
            return false;
        }
        service.saveEducator(new Educator(nameTextArea.getText(), degreeTextArea.getText()));
        return true;
    }
}

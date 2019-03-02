package view;

import model.Student;
import service.Service;

public class StudentPanel extends SavePanel {
    private final TitledTextArea nameTextArea = new TitledTextArea("Name");
    private final TitledTextArea ageTextArea = new TitledTextArea("Age");

    StudentPanel(HelloWorldSwing parent, Service service) {
        super(parent, service);
        addTextAreas();
        addButtonsAndStatusBar(parent);
    }

    @Override
    protected void addTextAreas() {
        addTextAreas(nameTextArea, ageTextArea);
    }

    @Override
    protected void emptyTextAreas() {
        emptyTextAreas(nameTextArea, ageTextArea);
    }

    @Override
    protected boolean save() {
        int age;
        try {
            age = Integer.parseInt(ageTextArea.getText());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        if (age < 0) {
            System.out.println("Age is negative: " + age);
            return false;
        }
        if (nameTextArea.getText().isEmpty()) {
            System.out.println("Name is empty");
            return false;
        }
        service.saveStudent(new Student(nameTextArea.getText(), age));
        return true;
    }
}

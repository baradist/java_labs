package view;

import service.Service;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

class MainPanel extends JPanel {
    private final JTextArea area;
    private final Service service;
    private final HelloWorldSwing parent;

    MainPanel(HelloWorldSwing parent, Service service) {
        this.parent = parent;
        this.service = service;
        area = new JTextArea();
        setLayout(new GridLayout(2, 1));
        add(getButtonsPanel(parent));
        add(new JScrollPane(area));
    }

    private JPanel getButtonsPanel(HelloWorldSwing parent) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));
        JButton studentData = new JButton("Student Data");
        studentData.addActionListener(e -> parent.switchToAddStudentPanel());
        buttonsPanel.add(studentData);
        JButton educatorData = new JButton("Educator Data");
        educatorData.addActionListener(e -> parent.switchToAddEducatorPanel());
        buttonsPanel.add(educatorData);
        JButton printButton = new JButton("Print the list");
        printButton.addActionListener(e -> {
            try {
                area.getDocument().remove(0, area.getDocument().getLength());
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            service.getPersons().forEach(s -> area.append(s.toString() + '\n'));
        });
        buttonsPanel.add(printButton);
        return buttonsPanel;
    }
}

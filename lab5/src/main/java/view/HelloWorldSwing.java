package view;

import service.Service;

import javax.swing.*;
import java.awt.*;

public class HelloWorldSwing extends JFrame {
    private JPanel mainPanel;
    private JPanel addStudentPanel;
    private JPanel addEducatorPanel;

    private HelloWorldSwing(String title) throws HeadlessException {
        super(title);
        createAndShowGui();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                () -> new HelloWorldSwing("Hello Swing"));
    }

    private void createAndShowGui() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initPanels();
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    private void initPanels() {
        mainPanel = new MainPanel(this, Service.getInstance());
        addStudentPanel = new StudentPanel(this, Service.getInstance());
        addEducatorPanel = new EducatorPanel(this, Service.getInstance());
    }

    void switchToAddStudentPanel() {
        setContentPane(addStudentPanel);
        validate();
    }

    void switchToMainPanel() {
        setContentPane(mainPanel);
        validate();
    }

    void switchToAddEducatorPanel() {
        setContentPane(addEducatorPanel);
        validate();
    }
}

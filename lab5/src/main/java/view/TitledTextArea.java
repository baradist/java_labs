package view;

import javax.swing.*;
import java.awt.*;

public class TitledTextArea extends JComponent {
    private JTextField editTextArea;
    private JTextArea uneditTextArea;

    public TitledTextArea(String title) {
        uneditTextArea = new JTextArea(title);
        editTextArea = new JTextField();

        setLayout(new BorderLayout());
        uneditTextArea.setEditable(false);

        editTextArea.setBackground(Color.WHITE);
        editTextArea.setForeground(Color.BLUE);

        add(uneditTextArea, BorderLayout.WEST);
        add(editTextArea, BorderLayout.CENTER);
    }

    public String getText() {
        return editTextArea.getText();
    }

    public void setText(String text) {
        editTextArea.setText(text);
    }
}

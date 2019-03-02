package view;

import service.Service;

import javax.swing.*;
import java.awt.*;

public abstract class SavePanel extends JPanel {
    private static final String ACCEPT = "Accept";
    private static final String CLEAR = "Clear";
    private static final String CLOSE = "Close";
    final Service service;
    private final TextArea statusBar;
    private final HelloWorldSwing parent;

    SavePanel(HelloWorldSwing parent, Service service) {
        this.parent = parent;
        this.service = service;
        statusBar = new TextArea();

        setLayout(new GridLayout(3, 1));
    }

    static void emptyTextAreas(TitledTextArea... textAreas) {
        for (TitledTextArea textArea : textAreas) {
            textArea.setText("");
        }
    }

    protected abstract void addTextAreas();

    protected abstract void emptyTextAreas();

    protected abstract boolean save();

    void addTextAreas(TitledTextArea... textAreas) {
        add(new JPanel() {{
            setLayout(new GridLayout(1, 2));
            for (TitledTextArea textArea : textAreas) {
                add(textArea);
            }
        }});
    }

    void addButtonsAndStatusBar(HelloWorldSwing parent) {
        add(new JPanel() {{
            setLayout(new GridLayout(1, 3));
            add(new JButton(ACCEPT) {{
                addActionListener(e -> {
                    if (save()) {
                        statusBar.setText("ACCEPTED");
                    } else {
                        statusBar.setText("NOT ACCEPTED");
                    }
                });
            }});
            add(new JButton(CLEAR) {{
                addActionListener(e -> {
                    emptyTextAreas();
                    statusBar.setText("");
                });
            }});
            add(new JButton(CLOSE) {{
                addActionListener(e -> {
                    emptyTextAreas();
                    statusBar.setText("");
                    parent.switchToMainPanel();
                });
            }});
        }});
        add(statusBar);
    }
}

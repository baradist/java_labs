package task2;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Notepad extends JFrame implements ActionListener {

    private static final String NP_TITLE = "Notepad.plus().plus()";
    private static final int NP_WIDTH = 640;
    private static final int NP_HEIGHT = 480;
    private static final int NP_BUTTONS_HEIGHT = 50;
    private static final int NP_BUTTONS_WIDTH = 400;
    private JTextArea area;
    private JButton openButton;
    private JFileChooser fc;
    private boolean changed = false;
    private JButton newButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JButton canselButton;
    private File openedFile;

    public Notepad(String title) throws HeadlessException {
        super(title);
        createAndShowGui();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                () -> new Notepad(NP_TITLE));
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
        updateTitle();
    }

    private void updateTitle() {
        setTitle(NP_TITLE + " "
                + (changed ? "* " : " ")
                + (openedFile == null ? "" : openedFile.getAbsolutePath()));
    }

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
        updateTitle();
    }

    private void createAndShowGui() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (rejectToSave()) {
                    return;
                }
                super.windowClosing(e);
                e.getWindow().dispose();
            }
        });
        area = new JTextArea();
        area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                setChanged(true);
            }
        });
        openButton = getAndRegisterButton("Open");
        newButton = getAndRegisterButton("New");
        saveButton = getAndRegisterButton("Save");
        saveAsButton = getAndRegisterButton("Save as");
        canselButton = getAndRegisterButton("Cansel");

        fc = new JFileChooser();

        setContentPane(new JPanel() {{
            setLayout(null);
            add(new JPanel() {{
                setLayout(new GridLayout(1, 5));
                add(newButton);
                add(openButton);
                add(saveButton);
                add(saveAsButton);
                add(canselButton);
                setSize(NP_BUTTONS_WIDTH, NP_BUTTONS_HEIGHT);
            }});
            add(new JScrollPane(area) {{
                setSize(NP_WIDTH, NP_HEIGHT - NP_BUTTONS_HEIGHT);
                setBounds(0, NP_BUTTONS_HEIGHT, NP_WIDTH, NP_HEIGHT - NP_BUTTONS_HEIGHT);
            }});
        }});
        setSize(NP_WIDTH, NP_HEIGHT);

        setVisible(true);
        updateTitle();
    }

    private JButton getAndRegisterButton(String open) {
        return new JButton(open) {{
            addActionListener(Notepad.this);
        }};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            newDocument();
        } else if (e.getSource() == openButton) {
            open();
        } else if (e.getSource() == saveButton) {
            save();
        } else if (e.getSource() == saveAsButton) {
            saveAs();
        } else if (e.getSource() == canselButton) {
            cansel();
        }
    }

    private boolean rejectToSave() {
        if (changed) {
            int result = JOptionPane.showConfirmDialog(
                    (Component) null, "The document was changed. Save it?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
            switch (result) {
                case JOptionPane.YES_OPTION:
                    save();
                    return changed;
                case JOptionPane.NO_OPTION:
                    return false;
                case JOptionPane.CANCEL_OPTION:
                    return true;
            }
        }
        return false;
    }

    private void newDocument() {
        if (rejectToSave()) {
            return;
        }
        setOpenedFile(null);
        try {
            area.getDocument().remove(0, area.getDocument().getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        setChanged(false);
    }

    private void open() {
        if (rejectToSave()) {
            return;
        }
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            setOpenedFile(fc.getSelectedFile());
            fillTextAreaFromFile();
        }
        setChanged(false);
    }

    private void save() {
        if (!changed) {
            return;
        }
        if (openedFile == null) {
            if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            setOpenedFile(fc.getSelectedFile());
        }
        writeFromTextAreaToFile();
        setChanged(false);
    }

    private void saveAs() {
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            setOpenedFile(fc.getSelectedFile());
            writeFromTextAreaToFile();
            setChanged(false);
        }
    }

    private void cansel() {
        if (!changed) {
            return;
        }
        if (openedFile == null) {
            try {
                area.getDocument().remove(0, area.getDocument().getLength());
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            fillTextAreaFromFile();
        }
        setChanged(false);
    }

    private void writeFromTextAreaToFile() {
        try (FileWriter writer = new FileWriter(openedFile)) {
            writer.write(area.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillTextAreaFromFile() {
        try (FileReader reader = new FileReader(openedFile)) {
            area.read(reader, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

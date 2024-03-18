import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SimpleNotepad extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;

    public SimpleNotepad() {
        setTitle("Notepad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("C:\\\\\\\\Users\\\\\\\\NGOC TAN\\\\\\\\Notepad.png");
        setIconImage(icon.getImage());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open", new ImageIcon("open.png"));
        JMenuItem saveItem = new JMenuItem("Save", new ImageIcon("save.png"));
        JMenuItem saveAsItem = new JMenuItem("Save As", new ImageIcon("save_as.png"));
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Open")) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                openFile(file);
            }
        } else if (e.getActionCommand().equals("Save")) {
            if (currentFile != null) {
                saveFile(currentFile);
            } else {
                saveFileAs();
            }
        } else if (e.getActionCommand().equals("Save As")) {
            saveFileAs();
        }
    }

    private void openFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            textArea.setText(sb.toString());
            currentFile = file;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFileAs() {
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file.exists()) {
                int option = JOptionPane.showConfirmDialog(this, "This file already exists. Do you want to overwrite it?", "Confirm Save As", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    saveFile(file);
                    currentFile = file;
                }
            } else {
                saveFile(file);
                currentFile = file;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleNotepad notepad = new SimpleNotepad();
            notepad.setVisible(true);
            notepad.setLocationRelativeTo(null);
        });
    }
}


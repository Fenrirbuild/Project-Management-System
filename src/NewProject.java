import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewProject extends systemExit {
    private JTextField projectTxt;
    private JLabel projectNametxt;
    private JFormattedTextField dateTxt;
    private JScrollPane descPanel;
    private JTextArea descriptionxt;
    private JPanel newProjectPanel;
    private JButton addButton;
    private JButton cancelButton;
    private DateFormat date = new SimpleDateFormat("dd/MM/yyyy");


    public NewProject(MainGUI mainFrame) {

        setContentPane(newProjectPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("New Project");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });

        //Action Listeners//
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (projectTxt.getText().equals("") || descriptionxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid project name and Description",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        addButtonisPressed(mainFrame);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonisPressed(mainFrame);
            }
        });
    }


    //Methods//
    private void cancelButtonisPressed(JFrame mainFrame) {
        System.out.println("Cancel Button is Pressed");
        unfreeze(mainFrame);

    }

    private void addButtonisPressed(JFrame mainFrame) throws ParseException {
        String projectName = projectTxt.getText();
        Date projectDate = date.parse(dateTxt.getText());
        String projectDescription = descriptionxt.getText();
        Main.projectH.createNewProject(projectName, projectDate, projectDescription);
        JOptionPane.showMessageDialog(this,
                "A new project " + projectName + " is added to the project list");
        unfreeze(mainFrame);


    }
}


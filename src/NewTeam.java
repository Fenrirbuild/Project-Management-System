import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewTeam extends systemExit {
    private JPanel TeamPanel;
    private JTextField nameTxt;
    private JPanel addPanel;
    private JTextArea descriptionTxt;
    private JButton addButton;
    private JButton cancelButton;

    public NewTeam(JFrame mainFrame) {

        setContentPane(TeamPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("New Team");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });

                    //-----Action Listeners---//
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameTxt.getText().equals("") || descriptionTxt.getText().equals("")) {

                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid team name and Description",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    addButtonIsPressed(mainFrame);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonIsPressed(mainFrame);

            }
        });
    }

    //-------------------Methods-----//


    private void addButtonIsPressed(JFrame mainFrame) {

        System.out.println("Adding new team");
        String teamName = nameTxt.getText();
        String teamDescription = descriptionTxt.getText();
        Main.teamH.newTeam(teamName, teamDescription);
        System.out.println("Team created \n" + Main.teamH.getTeams());
        JOptionPane.showMessageDialog(this,
                " A new team " + teamName + " with " + teamDescription + " is added to the team list");
        unfreeze(mainFrame);

    }

    private void cancelButtonIsPressed(JFrame mainFrame) {
        //todo implement cancelButtonPressed
        System.out.println("Cancel button is pressed");
        unfreeze(mainFrame);
    }
}

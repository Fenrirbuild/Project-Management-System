import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RemoveTeam extends systemExit {
    private JPanel mainPanel;
    private JComboBox teamcomboBox;
    private JButton removeButton;
    private JButton cancelButton;

    public RemoveTeam(MainGUI mainFrame) {
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Remove Team");
        updateTeamComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });
                //---Action Listeners---///
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeButtonPressed(mainFrame);

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonPressed(mainFrame);

            }
        });
    }

    private void removeButtonPressed(JFrame mainFrame) {
        String selectedTeam = teamcomboBox.getSelectedItem().toString();
        team foundTeam = Main.teamH.getTeam(selectedTeam);

        if (!foundTeam.getTeamName().equals("N/A")) {
            int teamIndex = Main.teamH.getTeams().indexOf(foundTeam);
            Main.teamH.deleteTeam(teamIndex);
            JOptionPane.showMessageDialog(null, " The team " + selectedTeam + " has been removed from the list",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Delete team button is pressed");
            unfreeze(mainFrame);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot delete a non-existing team",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            System.out.println("Cannot delete the team");
        }

    }

    private void cancelButtonPressed(JFrame mainFrame) {
        System.out.println("Cancel button is pressed");
        unfreeze(mainFrame);
    }

    private void updateTeamComboBox() {
        for (int i = 0; i < Main.teamH.getTeams().size(); i++) {
            teamcomboBox.addItem(Main.teamH.getTeams().get(i).getTeamName());
        }
    }
}

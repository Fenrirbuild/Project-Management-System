import javax.swing.*;
import java.awt.event.*;

public class RemoveProject extends systemExit {
    private JPanel removeProjectPanel;
    private JComboBox projectComboBox;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton cancelButton;

    public RemoveProject(MainGUI mainFrame) {
        setContentPane(removeProjectPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Remove Project");
        updateCombo();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });

        //-----------Action Listeners------------//

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {removeButtonISPressed(mainFrame);

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonisPressed(mainFrame);
            }
        });
    }

    //---Methods----//

    private void updateCombo() {
        System.out.println("Deleting a Project ");
        for (int i = 0; i < Main.projectH.getProjectList().size(); i++) {
            projectComboBox.addItem(Main.projectH.getProjectList().get(i).getProjectName());
        }
    }

    private void cancelButtonisPressed(JFrame mainFrame) {
        System.out.println("Cancel Button ia Pressed");
        unfreeze(mainFrame);

    }

    private void removeButtonISPressed(MainGUI mainFrame) {

        System.out.println("Remove Project button is pressed");
        String selectedProject = projectComboBox.getSelectedItem().toString();
        project foundProject = Main.projectH.findingTheProject(selectedProject);
        int projectIndex = Main.projectH.getProjectList().indexOf(foundProject);

        Main.projectH.removeProject(projectIndex); // let it sit for a while
        JOptionPane.showMessageDialog(this,
                " The project " + selectedProject + " is removed from the project list");
        mainFrame.updateLoadedProject();
        unfreeze(mainFrame);
    }
}

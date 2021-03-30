import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OpenProject extends systemExit {
    private JPanel ProjectPanel;
    private JPanel buttonPanel;
    private JButton openButton;
    private JButton cancelButton;
    private JPanel selectPanel;
    private JComboBox projectCombo;
    private JPanel openPanel;

    public OpenProject(MainGUI mainFrame) {

        setContentPane(openPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Open a Project");
        updateComboBox(); // not yet in use because of a handler
        setVisible(true);


//------------------Keep this exit listener here it looks better------//
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });

//---------Action listeners for the form-------------//
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cancelButtonIsPressed(mainFrame);}


        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {openButtonIsPressed(mainFrame);

            }
        });




    }
    //Voids for buttons//

    // Do something about the open button
    private void updateComboBox(){
        System.out.println("Updating project selection");
        for(int i=0;i<Main.projectH.getProjectList().size();i++){

            projectCombo.addItem(Main.projectH.getProjectList().get(i).getProjectName());
        }
    }

    private void cancelButtonIsPressed(MainGUI mainFrame) {
        System.out.println("Cancel Button is Pressed");
        unfreeze(mainFrame);
    }

    private void openButtonIsPressed(MainGUI mainFrame){
        System.out.println("Opening a project");
        String selectedProject = projectCombo.getSelectedItem().toString();
        Main.projectH.selectProject(selectedProject);
        mainFrame.updateLoadedProject();
        unfreeze(mainFrame);
    }
}

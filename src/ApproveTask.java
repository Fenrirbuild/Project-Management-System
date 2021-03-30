import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ApproveTask extends systemExit {
    private JPanel approveTaskPanel;
    private JComboBox taskComboBox;
    private JPanel topPanel;
    private JButton approveButton;
    private JButton cancelButton;
    private JPanel buttonPanel;


    public ApproveTask(MainGUI mainFrame) {
        setContentPane(approveTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Approve task");
        getComboList();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });

        //---------------Action Listeners-----------//
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveButtonIsPressed(mainFrame);

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonisPressed(mainFrame);

            }
        });
    }

    //-------------------Methods---------------------//
    private void cancelButtonisPressed(JFrame mainFrame) {
        System.out.println("Cancel Button is Pressed");
        unfreeze(mainFrame);

    }

    private void approveButtonIsPressed(MainGUI mainFrame) {
        try {
            System.out.println("Approve task button is Pressed");
            String selectedTask = taskComboBox.getSelectedItem().toString();
            Main.taskH.completeTask(selectedTask, mainFrame);
            JOptionPane.showMessageDialog(this,
                    "The task " + selectedTask + " has been marked as completed ");
            unfreeze(mainFrame);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There is no available task in this project to approve",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void getComboList() {
        System.out.println("Approving task, new list updating");
        for (int i = 0; i < Main.taskH.getTaskList().size(); i++) {
            if (Main.taskH.getTaskList().get(i).getStatus() == TaskStatus.ACTIVE) {
                taskComboBox.addItem(Main.taskH.getTaskList().get(i).getTaskName());
            }
        }
    }
}

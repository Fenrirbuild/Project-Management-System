import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RemoveTask extends systemExit {
    private JPanel removeTaskPanel;
    private JComboBox taskComboBox;
    private JButton removeButton;
    private JButton cancelButton;

    public RemoveTask(MainGUI mainFrame) {
        setContentPane(removeTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Remove Task");
        updateTaskComboBox();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });
        //------------Action Listeners-------//
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeButtonIsPressed(mainFrame);

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonisPressed(mainFrame);

            }
        });
    }

    //-------------------Methods----------------------------------------------------------------
    private void cancelButtonisPressed(JFrame mainFrame) {
        System.out.println("Cancel Button is Pressed");
        unfreeze(mainFrame);

    }

    private void removeButtonIsPressed(MainGUI mainFrame) {
        try {
            String selectedTask = taskComboBox.getSelectedItem().toString();
            task foundTask = Main.taskH.findTask(selectedTask);
            int taskIndex = Main.taskH.getTaskList().indexOf(foundTask);
            Main.taskH.deleteTask(taskIndex, selectedTask, mainFrame);
            System.out.println("Delete task button is Pressed");
            JOptionPane.showMessageDialog(this,
                    " The task " + selectedTask + " has been removed from the list ");
            unfreeze(mainFrame);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There is no available task to remove",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            unfreeze(mainFrame);
        }
    }

    private void updateTaskComboBox() {
        for (int i = 0; i < Main.taskH.getTaskList().size(); i++) {
            taskComboBox.addItem(Main.taskH.getTaskList().get(i).getTaskName());
        }
    }

}

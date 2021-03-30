import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class NewTask extends systemExit {
    private JPanel addTaskPanel;
    private JPanel actionPanel;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel inputPanel;
    private JTextField taskNameTxt;
    private JComboBox teamComboBox;
    private JScrollPane descriptionScrollPanel;
    private JTextArea descriptionTxt;
    private JTextField DurationTxt;
    private JTextField priorityTxt;
    private JPanel newTaskPanel;
    private team team;
    private String taskName;
    private int estimatedDays;
    private String taskDescription;
    private String priority;;
    private String teamName;

    public NewTask(MainGUI mainFrame) {

        setContentPane(addTaskPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("New Task");
        updateTeamCombo();
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                unfreeze(mainFrame);
            }
        });


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (taskNameTxt.getText().equals("") || DurationTxt.getText().equals("") || teamComboBox.getSelectedItem().equals("N/A")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid Task name, Team and Duration",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
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

    //-------------------Methods--------------//
    private void cancelButtonIsPressed(JFrame mainFrame) {
        System.out.println("Cancel Button is Pressed");
        unfreeze(mainFrame);
// Add the open bit
    }
    private void updateTeamCombo() {
        for (int i=0; i<Main.teamH.getTeams().size();i++) {
            teamComboBox.addItem(Main.teamH.getTeams().get(i).getTeamName());
        }
    }
    private void addButtonIsPressed(MainGUI mainFrame) throws NumberFormatException {
        System.out.println("New Task Button is Pressed");
        taskName = taskNameTxt.getText();
        estimatedDays = Integer.parseInt(DurationTxt.getText());
        taskDescription = descriptionTxt.getText();
        teamName = teamComboBox.getSelectedItem().toString();
        team = Main.teamH.getTeam(teamName);
        priority = priorityTxt.getText();
        ArrayList<task> successor = getSuccessor();
        Main.taskH.createTask(taskName, estimatedDays, team, taskDescription, priority, mainFrame);
        JOptionPane.showMessageDialog(this,
                " A new task " + taskName + " assigned to the " + teamName + " with the description of  " + taskDescription + " and an estimate of " + estimatedDays + " days has been added to the list");
        unfreeze(mainFrame);
    }
    private ArrayList<task> getSuccessor() {

        ArrayList<task> returnList = new ArrayList<>();

        if(priority.isEmpty()){
            return returnList;
        }else{
            //split the string, also in the task handler
            String[] temp = priority.split(", ");
            ArrayList<task> thTask = Main.taskH.getTaskList();

            for(int i = 0; i< temp.length;i++){
                for(int j=0;j<thTask.size();j++){
                    if(temp[i] == thTask.get(j).getTaskName()) {
                        returnList.add(thTask.get(j));
                        System.out.println("Added a Successor for the task: " + thTask.get(j));
                    }
                }
            }
            return returnList;
        }


    }
}

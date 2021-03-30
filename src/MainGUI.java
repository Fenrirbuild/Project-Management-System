import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
public class MainGUI extends JFrame {

    private JPanel MainPanel;
    private JTextField LoadedprjTxt;
    private JTextField EstTxt;
    private JButton openProjectButton;
    private JButton removeTeamButton;
    private JButton newProjectButton;
    private JButton newTaskButton;
    private JButton removeTaskButton;
    private JButton newTeamButton;
    private JButton manageTeamButton;
    private JButton removeProjectButton;
    private JButton approveTaskButton;
    private JPanel StatusPanel;
    private JPanel ButtonPanel;
    private JPanel TaskPanel;
    private JTable activeTaskTable;
    private JTable pendingTaskTable;
    private JScrollPane activeTaskPanel;
    private JScrollPane pendingTaskPanel;
    private JTable completedTaskTable;


    private DefaultTableModel activeModel;
    private DefaultTableModel pendingModel;
    private DefaultTableModel completeModel;

    public MainGUI() {
        newTaskButton.setEnabled(false);
        approveTaskButton.setEnabled(false);
        removeTaskButton.setEnabled(false);
        System.out.println("System is working. . . .");
        LoadedprjTxt.setText("No project currently open");
        setContentPane(MainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(" JVM Project Management System Coursework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        createTable("active");
        createTable("pending");
        createTable("completed");
        // Action listeners for all the Buttons for each form  //

        newProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newProjectButtonIsPressed();
            }
        });
        removeProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProjectButtonIsPressed();
            }
        });
        newTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTaskButtonIsPressed();

            }
        });
        approveTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveTaskButtonIsPressed();

            }
        });
        removeTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeTaskButtonIsPressed();

            }
        });
        newTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTeamButtonIsPressed();

            }
        });
        removeTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {removeTeamButtonIsPressed();

            }
        });
        openProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProjectButtonIsPressed();
            }
        });
    }

    public void updateLoadedProject() {
        // TODO: 11/18/2020
        // update the loaded project into the text field
        LoadedprjTxt.setText(Main.projectH.getCurrentProject().getProjectName());
        System.out.println(" Updated loaded ProjectTxt");
        updateTaskPanels();
    }

    public void updateTaskPanels() {
        Main.taskH.updateTaskTables(activeModel, pendingModel, completeModel);
        System.out.println("Task Panels Updated");
        toggle();
    }


    public void toggle() {
        newTaskButton.setEnabled(true);
        approveTaskButton.setEnabled(true);
        removeTaskButton.setEnabled(true);
    }

    private void createTable(String type) {
        System.out.println("Creating Table...");

        if (type == "active") {                                                           //creating active task table
            String[] columnNames = {"Task Name", "Team assigned", "Duration(in days)", "Time Left"};
            activeTaskTable.setModel(new DefaultTableModel(null, columnNames));

            activeModel = (DefaultTableModel) activeTaskTable.getModel();

            System.out.println("...Active tasks Table is being Created");


        } else if (type == "pending") {                                                    //Creating pending task table


            String[] columnNames = {"Task Name", "Team assigned", "Estimated Time (Days)", "Priority Task" };
            pendingTaskTable.setModel(new DefaultTableModel(null, columnNames));
            pendingModel = (DefaultTableModel) pendingTaskTable.getModel();
            System.out.println("...Pending tasks table is being created");


        } else if (type == "completed") {

            String[] columnNames = {"Task Name", "Team assigned", "Duration(in days)", "Time spent"};
            completedTaskTable.setModel(new DefaultTableModel(null, columnNames));
            completeModel = (DefaultTableModel) completedTaskTable.getModel();
            System.out.println("...Completed tasks table is being created");
        } else {
            System.out.println(" Table could not be created");
        }

    }

    //Don't touch yet
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    //Voids for Buttons calling the GUI forms//
    private void newProjectButtonIsPressed() {
        NewProject popout = new NewProject(this);
        this.setEnabled(false); // So this gui remains unchanged
        System.out.println("New project button pressed");

    }

    private void openProjectButtonIsPressed() {
        System.out.println("Opening a Project");
        OpenProject popout = new OpenProject(this);
        this.setEnabled(false);

    }

    private void removeProjectButtonIsPressed() {
        System.out.println("Remove project button is pressed");
        RemoveProject popout = new RemoveProject(this);
        this.setEnabled(false);
    }

    private void newTaskButtonIsPressed() {
        System.out.println("New task button is pressed");
        NewTask popout = new NewTask(this);
        this.setEnabled(false);
    }

    private void approveTaskButtonIsPressed() {
        System.out.println("Approve task button is pressed");
        ApproveTask popout = new ApproveTask(this);
        this.setEnabled(false);

    }

    private void removeTaskButtonIsPressed() {
        System.out.println("Remove task button is pressed");
        RemoveTask popout = new RemoveTask(this);
        this.setEnabled(false);
    }

    private void newTeamButtonIsPressed() {
        System.out.println("New team button pressed");
        NewTeam popout = new NewTeam(this);
        this.setEnabled(false);
    }
    private void removeTeamButtonIsPressed() {
        System.out.println("Remove team button pressed");
        RemoveTeam popout = new RemoveTeam(this);
        this.setEnabled(false);
    }
}
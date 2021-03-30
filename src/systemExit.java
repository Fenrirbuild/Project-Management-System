import javax.swing.*;

public class systemExit extends JFrame {

// I have no idea why the system freezes but this works so just let it be
    // A dispose(); works but the Main gui still gets frozen
    // Extend all forms with this until they stop freezing

    public void unfreeze(JFrame mainFrame) {
        System.out.println("Closing the main Window");
        mainFrame.setEnabled(true);
        this.dispose();
    }
}

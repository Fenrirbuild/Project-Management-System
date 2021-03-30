
import javax.swing.UIManager.*;
public class Main {


   /*  GUI using Java - Done
    Create Persistence class using Kotlin -Done
    Implement data Persistence- Done
    Be able to add contents of .txt files to tables -Done
    Create data classes -Done
    Critical Path with Kotlin and scala-NOT DONE



  */

    // TODO: 11/30/2020
    // Critical Path with scala and kotlin
    // Some more error handling

    public static projectHandler projectH;
    public static teamHandler teamH;
    public static taskHandler taskH;

    public static void main(String[] args) {

        projectH = new projectHandler();
        taskH = new taskHandler();
        teamH = new teamHandler();
        new MainGUI();

    }

}

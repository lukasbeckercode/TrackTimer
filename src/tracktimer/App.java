package tracktimer;
import javax.swing.*;

public class App {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(()->{
                myFrame frame = new myFrame();
            frame.setVisible(true); //make the window visible
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if someone closes the APP, actually close it
            frame.setSize(500,500); //Set the Size
            frame.setTitle("Track Timer"); //Set the Title
                });
    }
}

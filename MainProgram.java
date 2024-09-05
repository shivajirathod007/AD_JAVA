import javax.swing.*;

public class MainProgram extends JFrame {
    MainProgram() {
        setVisible(true);
        setSize(500, 500);
        setTitle("Ticket Booking System");
        JButton jb = new JButton("trial");
        add(jb);
    }

    public static void main(String arg[]) {
        MainProgram mp = new MainProgram();
    }
}

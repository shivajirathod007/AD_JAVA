import javax.swing.*;

public class MainProgram extends JFrame {
    MainProgram() {
        setVisible(true);
        setSize(500, 500);
        setTitle("Ticket Booking System");
        JButton jb = new JButton("trial");
        JLabel jl=  new JLabel();
        add(jb);
        add(jl);
    }

    public static void main(String arg[]) {
        MainProgram mp = new MainProgram();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MovieTheaterApp extends JFrame {

    String[][] theaterData = {
            { "Bollywood Multilpex, Kharadi", "10:00 AM", "12:00 AM", "Mission Mangal", "Avengers", "Interstellar" },
            { "INOX PVR, Aundh", "11:00 AM", "01:00 AM", "Matrix", "Titanic", "P.K" },
            { "Pheonix PVR Cinemas, Vimannagar", "09:30 AM", "11:30 PM", "Golmaal", "Toy Story", "The Lion King" },
            { "Mangala Cinema, Pune", "10:30 AM", "12:30 AM", "Batman", "Jackpot", "Wonder Woman" },
            { "Rahul 70 mm, JM Road", "08:00 AM", "10:00 PM", "Harry Potter", "Chori Chori Chupke Chupke",
                    "Lord of the Rings" }
    };

    String[][] movieTimings = {
            { "10:00 AM", "02:00 PM", "07:00 PM" },
            { "11:00 AM", "04:00 PM", "08:00 PM" },
            { "09:30 AM", "02:30 PM", "07:00 PM" },
            { "10:30 AM", "03:00 PM", "07:30 PM" },
            { "08:00 AM", "11:00 AM", "03:30 PM" }
    };

    MovieTheaterApp() {
        setTitle("Movie Theaters in PUNE");
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        JLabel headerLabel = new JLabel("Choose a Theater to see its detail !!!", JLabel.CENTER);
        headerLabel.setBackground(Color.white);
        headerLabel.setForeground(Color.black);
        headerLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));

        add(headerLabel);

        for (int i = 0; i < 5; i++) {
            JButton theaterButton = new JButton(theaterData[i][0]);

            theaterButton.setFont(new Font("Serif", Font.BOLD, 28)); // Set Font Style and Size
            theaterButton.setBackground(Color.DARK_GRAY); // Set Button Background Color
            theaterButton.setForeground(Color.WHITE);

            add(theaterButton);

            final int theaterIndex = i; // needed for inner class access
            theaterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showTheaterDetails(theaterIndex);
                }
            });
        }
    }

    public void showTheaterDetails(int theaterIndex) {
        JFrame detailFrame = new JFrame(theaterData[theaterIndex][0] + " Details");

        detailFrame.setSize(700, 500);
        detailFrame.setLayout(new GridLayout(6, 1));

        // detailFrame.setFont(new Font("Serif", Font.BOLD,30));

        detailFrame.add(new JLabel("Theater Name: " + theaterData[theaterIndex][0]));
        detailFrame.add(new JLabel("Opening Time: " + theaterData[theaterIndex][1]));
        detailFrame.add(new JLabel("Closing Time: " + theaterData[theaterIndex][2]));

        for (int i = 3; i < 6; i++) {
            detailFrame.add(
                    new JLabel("Movie: " + theaterData[theaterIndex][i] + " at " + movieTimings[theaterIndex][i - 3]));
        }

        detailFrame.setVisible(true);
    }

}

public class MainProgram {
    public static void main(String[] ajp) {
        // new MovieTheaterApp(); // ---- call this model when it reqiure
    }
}
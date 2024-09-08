import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface InnerMainProgram {

    JButton b1[] = new JButton[6]; // Initialize the array
    JButton BOOK_THE_SEAT = new JButton("BOOk_SELECTED_SEAT");
    JLabel label1 = new JLabel();

    public List<String> selectedSeats = new ArrayList<>();
}

class MovieTheaterApp extends JFrame implements InnerMainProgram, ActionListener {

    String[][] theaterData = {
            { "Bollywood Multilpex, Kharadi", "10:00 AM", "12:00 PM", "Mission Mangal", "Avengers", "Interstellar" },
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
                    // pooja local host is connected
                }
            });
        }
    }

    public void showTheaterDetails(int theaterIndex) {
        JFrame detailFrame = new JFrame(theaterData[theaterIndex][0] + " Details");
        JLabel JLabel1, JLabel2, JLabel3;
        JLabel1 = new JLabel(("Theater Name: " + theaterData[theaterIndex][0]));
        JLabel2 = new JLabel(("Theater Open Time: " + theaterData[theaterIndex][1]));
        JLabel3 = new JLabel(("Theater Cose Time: " + theaterData[theaterIndex][2]));
        detailFrame.setSize(700, 500);

        detailFrame.setLayout(null);
        detailFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        detailFrame.setFont(new Font("Arial", Font.BOLD, 50));

        detailFrame.add(JLabel1);
        detailFrame.add(JLabel2);
        detailFrame.add(JLabel3);
        JLabel1.setBounds(20, 20, 500, 50); // Theater Name
        JLabel2.setBounds(20, 60, 500, 50); // Opening Time
        JLabel3.setBounds(20, 100, 500, 50); // Closing Time
        b1[3] = new JButton("BOOK");
        b1[4] = new JButton("BOOK");
        b1[5] = new JButton("BOOK");
        for (int i = 3; i < 6; i++) {
            JLabel movieLabel = new JLabel(
                    "Movie: " + theaterData[theaterIndex][i] + " at " + movieTimings[theaterIndex][i - 3]);
            movieLabel.setBounds(20, 140 + (i - 3) * 40, 400, 50); // Adjusting vertical placement for each movie
            b1[i].setBounds(450, 160 + (i - 3) * 40, 80, 20);
            detailFrame.add(movieLabel);
            detailFrame.add(b1[i]);
            b1[i].addActionListener(this);

        }

        detailFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1[3] || ae.getSource() == b1[4] || ae.getSource() == b1[5]) {
            new SeatSelection();
        }
    }

}

class SeatSelection extends JFrame implements InnerMainProgram, ActionListener {
    private static final int ROWS = 7;
    private static final int COLS = 7;

    // 2D array of JButtons to represent the seats
    private JButton[][] seats = new JButton[ROWS][COLS];

    SeatSelection() {
        setTitle("Seat Selection");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS, 10, 10)); // Create a grid layout for the seats

        // Create and add buttons to the frame
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 2; col++) {
                seats[row][col] = new JButton("Seat " + (row * COLS + col + 1)); // Button label
                seats[row][col].setBackground(Color.LIGHT_GRAY); // Default background color

                seats[row][col].addActionListener(new SeatSelectionHandler());

                add(seats[row][col]); // Add button to JFrame

            }

        }
        add(BOOK_THE_SEAT);
        BOOK_THE_SEAT.addActionListener(this);
        add(label1);
        label1.setBounds(10, 600, 200, 50);
        setVisible(true);

    }

    private class SeatSelectionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedButton = (JButton) e.getSource();

            if (selectedButton.getBackground() == Color.LIGHT_GRAY) {
                selectedButton.setBackground(Color.GREEN); // Mark as selected
                selectedSeats.add(selectedButton.getText());
            } else {
                selectedButton.setBackground(Color.LIGHT_GRAY); // Deselect
                selectedSeats.remove(selectedButton.getText());
            }
            String str = "";
            str += selectedButton.getText();
            label1.setText("You have selceted : " + str);

        }

    }

    public class MainProgram {
        public static void main(String[] ajp) {
            new MovieTheaterApp(); // ---- call this model when it reqiure
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BOOK_THE_SEAT) {
            // Show a message dialog with all the selected seats
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No seats selected!");
            } else {
                JOptionPane.showMessageDialog(this, "Successfully BOOKED: " + String.join(", ", selectedSeats));
            }
        }

    }

}
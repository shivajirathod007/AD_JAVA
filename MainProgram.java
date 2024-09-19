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

class JprogressBar {
    JProgressBar jb;
    int i = 0, num = 0;

    JprogressBar() {
        jb = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        jb.setBounds(40, 40, 160, 30);
        jb.setValue(0);
        jb.setStringPainted(true);

    }

    public void iterate() {
        jb.setVisible(true);
        while (i <= 100) {
            jb.setValue(i);
            i = i + 10;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }
}

class MovieTheaterApp extends JFrame implements InnerMainProgram, ActionListener {

    String[][] theaterData = {
            { "Bollywood Multilpex, Kharadi", "10:00 AM", "12:00 PM", "Mission Mangal", "Avengers",
                    "Interstellar" },
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
        JLabel1.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel2.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel3.setFont(new Font("Arial", Font.BOLD, 15));
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
            movieLabel.setBounds(20, 160 + (i - 3) * 40, 400, 50);
            movieLabel.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
            movieLabel.setForeground(Color.RED);
            b1[i].setBounds(450, 180 + (i - 3) * 40, 80, 20);
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

<<<<<<< HEAD
public class MainProgram {
    public static void main(String[] ajp) {
         new MovieTheaterApp(); 
=======
class SeatSelection extends JFrame implements InnerMainProgram {
    private static final int ROWS = 7;
    private static final int COLS = 7;

    // 2D array of JButtons to represent the seats
    private JButton[][] seats = new JButton[ROWS][COLS];
    private JButton backButton = new JButton("Back");

    private JProgressBar progressBar = new JProgressBar(0, 100);

    SeatSelection() {
        setTitle("Seat Selection");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(null);

        int seatnum = 1;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 2; col++) {
                seats[row][col] = new JButton("seat: " + seatnum);
                seats[row][col].setBackground(Color.DARK_GRAY);
                seats[row][col].setForeground(Color.WHITE);
                // seats[row][col].setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));

                seats[row][col].setBounds(50 + col * 80, 50 + row * 60, 80, 50);

                seats[row][col].addActionListener(new SeatSelectionHandler());

                add(seats[row][col]);
                seatnum++;
            }

        }
        add(BOOK_THE_SEAT);
        BOOK_THE_SEAT.setBounds(400, 500, 200, 50);
        add(label1);
        // label1.setBounds(20, 600, 400, 50);

        setVisible(true);

        backButton.setBounds(50, 500, 100, 50);

        add(backButton);
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent aEvent) {

                dispose();

            }
        });

        BOOK_THE_SEAT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    progressBar.setBounds(200, 570, 300, 30);
                    progressBar.setStringPainted(true);
                    add(progressBar);
                    new ProgressWorker().execute();
                } catch (Exception ee) {
                    System.out.println(e);
                }

            }
        });

    }

    private class SeatSelectionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedButton = (JButton) e.getSource();

            if (selectedButton.getBackground() == Color.DARK_GRAY) {
                selectedButton.setBackground(Color.GREEN); // Mark as selected
                selectedSeats.add(selectedButton.getText());
            } else {
                selectedButton.setBackground(Color.DARK_GRAY); // Deselect
                selectedSeats.remove(selectedButton.getText());
            }

            label1.setText("tatal payment is ($500 for each seat)  : " + (selectedSeats.size()) * 500);
            label1.setBounds(10, 600, 400, 50);
        }

    }

    class ProgressWorker extends SwingWorker<Void, Integer> {
        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i <= 100; i += 10) {
                Thread.sleep(100); // Simulate time-consuming task
                publish(i);
                progressBar.setString(Integer.toString(i) + "%");
            }
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            for (Integer chunk : chunks) {
                progressBar.setValue(chunk); // Update the progress bar value
            }
        }

        @Override
        protected void done() {
            // When the task is complete, show a dialog
            JOptionPane.showMessageDialog(SeatSelection.this, "Payment dones...");
            progressBar.setValue(0);
            progressBar.setVisible(false);

            BOOK_THE_SEAT.setEnabled(true);
        }
    }

    // main method
    public class MainProgram {
        public static void main(String[] ajp) {
            new MovieTheaterApp(); // ---- call this model when it reqiure
        }
>>>>>>> 2891ce7846562df9cad4f4f4b3d74342ff07d78c
    }
}
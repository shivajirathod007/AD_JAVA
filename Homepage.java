import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;




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

class SeatSelection extends JFrame implements InnerMainProgram {
    void payment() {
        JFrame f1 = new JFrame("Payment Gateway");
        f1.setSize(505, 505);
        f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f1.setVisible(true);
        f1.setLayout(null);

        BufferedImage image;
        JLabel imageContainer, l1;
        JTextField payField = new JTextField("");
        JButton payButton = new JButton("Proceed");
        l1 = new JLabel("Enter payment ID: ");

        l1.setBounds(100, 50, 150, 30);
        payField.setBounds(250, 50, 150, 30);
        payButton.setBounds(200, 400, 100, 30);

        f1.add(l1);
        f1.add(payField);
        f1.add(payButton);

        try {
            image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\QR.jpg"));
            imageContainer = new JLabel(new ImageIcon(image));
            imageContainer.setBounds(120, 120, image.getWidth(), image.getHeight());
            f1.add(imageContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedSeats.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Select Seat first");
                } else {

                    JOptionPane.showMessageDialog(null,
                            "Payment Done of Rs(each 500): " + (selectedSeats.size() * 500));
                    f1.dispose();
                }

            }
        });
    }

    private static final int ROWS = 7;
    private static final int COLS = 7;

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

                seats[row][col].setBounds(50 + col * 80, 50 + row * 60, 80, 50);

                seats[row][col].addActionListener(new SeatSelectionHandler());

                add(seats[row][col]);
                seatnum++;
            }

        }
        add(BOOK_THE_SEAT);
        BOOK_THE_SEAT.setBounds(400, 500, 200, 50);
        add(label1);

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
                    payment();
                } catch (Exception ee) {
                    System.out.println(ee);
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

            label1.setText("total payment is ($500 for each seat)  : " + (selectedSeats.size()) * 500);
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
            // JOptionPane.showMessageDialog(SeatSelection.this, "Payment dones...");

            progressBar.setValue(0);
            progressBar.setVisible(false);

            BOOK_THE_SEAT.setEnabled(true);
        }
    }

    // main method

}

class Movies {

    private static final String m_name[] = {
            "COCO", "Salma's Big Wish", "The Lion King", "Pirates of The Silicon Valley", "The Billion Dollar Code", 
            "Mission Mangal", "Bhaag Milkha Bhaag", "Munjya"
    };

    private static final String m_path[] = {
            "C:\\\\Users\\\\Admin\\\\Desktop\\\\Advanced java\\\\Advanced java\\\\coco.jpg",
        "C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\salmas.jpg",
        "C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\lion.jpg",
       "C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\pirates.jpg",
       "C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\billion.jpg",
      "C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\mission.jpg",
      "C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\milkha.jpg ",
       "C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\munjya.jpg",
    };

    private static final String m_description[] = {
        "   COCO is the sprightly story of a young boy who wants to be a musician and somehow finds himself communing with talking skeletons in the land of the dead. The story follows a 12-year-old boy named Miguel who is accidentally transported to the Land of the Dead, where he seeks the help of his deceased musician great-great-grandfather to return him to his family among the living and to reverse his family's ban on music.\r\n"  
        + "                 \r\n" + 
         " Despite his familys longstanding ban on music and the affection he holds for them, 12-year-old Miguel is desperate to play and cant help but go against their wishes. Aspiring to be like his idol, the celebrated Ernesto de la Cruz, Miguel attempts to borrow the deceased star guitar to play in a village talent contest. Upon doing so, he finds himself transported into the Land of the Dead where he meets past generations of his family. However, he only has until sunrise to find a way out, otherwise he will be forever trapped in the afterlife. Coco is a colourful celebration of the importance of family, the significance of memories and the power of music which encourages viewers to seize the moment and follow your dreams",
        "\r\n" + 
         "       Salma lives in an orphanage with her two foster brothers, Jorge and Pedro. She doesnt know who her parents were, but she is forbidden from building an altar on the Day of the Dead. One day, a clue leads her to investigate a nearby abandoned castle. There, she finds an old spell book that transports her, Jorge and Pedro to the Land of the Dead. The takeaway from the film is that death is not something to be feared, but something to be welcomed and even celebrated. Theres an atmosphere of joy that emanates both from the Day of the Dead and the Land of the Dead. Pedro comments that he is surprised by how joyful the afterworld is, and Salma learns to accept her parents passing.",
          " Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.\r\n" + 
                    " \r\n" + 
                              "  A young lion prince is cast out of his pride by his cruel uncle, who claims he killed his father. While the uncle rules with an iron paw, the prince grows up beyond the Savannah, living by a philosophy: No worries for the rest of your days. But when his past comes to haunt him, the young prince must decide his fate: Will he remain an outcast or face his demons and become what he needs to be?",
        "\r\n" + 
          "   This film tells the story of the beginning of the founding of Microsoft and Apple by the three main characters at that time, Steve Jobs, Bill Gates, and Steve Wozniak. This film also tells of several conflicts and disputes over the theft of ideas that some of the above characters did when they founded their first company.\r\n" + //
                       "\r\n" + 
                  "     What to Learn: From this film, try to understand some of the history of the operating system and the world’s giant company in its early days. For example, who was the first to have an operating system based on a graphical user interface and mouse (GUI)? What are the fundamental differences between early Apple computers and Windows?",
        "\r\n" + 
    "      This mini-series tells the story of a conflict dispute between Google and a German startup regarding the algorithm used on Google Maps. Regardless of whether this dispute is actual or not, this story provides essential lessons regarding patents in technology and copyright protection. The most exciting part is in the last episode when the trial takes place, where many technical discussions are carried out — for example, related to the basis of the zooming algorithm used by Google Maps and its relationship to data structures and computer graphics.\r\n" + //
                       "\r\n" + 
        "                 What to learn: You can see how the software industry collaborates, from prototyping to maintaining finished products. An important aspect that you need to look at is how the programmer must also consider the performance and resource load of the program he designed to be used by the user efficiently.",
      "\r\n" + 
        "      'Mission Mangal', a new Indian Hindi-language film, tells the dramatic true story of the women behind India's first mission to Mars. \r\n" + //
                       "\r\n" + 
        "         Launched in 2013, the Mars Orbiter Mission (MOM), also called Mangalyaan, was India's first interplanetary mission and the first time that any country successfully reached Martian orbit on the first try. India made history again this past week when, on Aug. 21, 2019, ISRO's Chandrayaan-2 spacecraft snapped its first picture of the moon. ",
                       " \r\n  " +
               "    Bhaag Milkha Bhaag is about the truth behind the ascension of Milkha \"The Flying Sikh\" Singh who was scarred because of the India-Pakistan partition.The film starts in the 1960 Summer Olympics in Rome, where a coach says Bhaag Milkha Bhaag! The story is taken back to the memories of the childhood days of a young boy which haunted him, resulting in him dropping to fourth. Partition of India in 1947 caused chaos which resulted in mass religious violence in Punjab in British India, killing the parents of Milkha Singh (Farhan Akhtar).  ",
        " \r\n " +
          "      A young man's visit to his native village unveils a family secret and a vengeful spirit, Munjya, who wants to get married. Now the young man must fight to protect himself and his love from Munjya's clutches leading to a humorously chaotic and terrifying adventure",
                
    };
    
    private static final String m_trailer[] = {
            "https://youtu.be/969WVyvgzx4", "https://youtu.be/VQtImxhxW6U?t=3", 
            "https://youtu.be/lFzVJEksoDY?t=1", "https://youtu.be/08x8NZ86Gp0?t=5",
            "https://youtu.be/iDvPvqImb-4?t=4", "https://youtu.be/q10nfS9V090?t=5", 
            "https://youtu.be/0nR9JS2WsNg?t=3", "https://youtu.be/8X3uF80H5LU?t=8"
    };

    private static final String m_date[] = {
            "2024-24-09", "2024-20-09", "2024-10-10", "2024-11-10", 
            "2024-12-11", "2024-28-09", "2024-12-10", "2024-29-10"
    };

    private static final String m_time[] = {
            "12:30 PM", "4:00 PM", "3:45 PM", "2:00 PM", 
            "2:30 PM", "3:00 PM", "11:30 AM", "1:00 PM"
    };

    // Constructor
    public Movies() {
        upcoming(); 
    }

 public void upcoming() {
    JFrame frame = new JFrame("Upcoming Movies");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(900, 800);
    frame.setLayout(new BorderLayout());
    frame.setBackground(new Color(0, 0, 0));

    final JPanel cardPanel = new JPanel();
    final CardLayout cardLayout = new CardLayout();
    cardPanel.setLayout(cardLayout);
    cardPanel.setBackground(new Color(0, 0, 0));

    // Create movie cards
    for (int i = 0; i < m_name.length; i++) {
        final int index = i;

        JPanel movieCard = new JPanel();
        movieCard.setLayout(new BoxLayout(movieCard, BoxLayout.Y_AXIS));
        movieCard.setBackground(new Color(0, 0, 0));
        movieCard.setBorder(BorderFactory.createLineBorder(Color.red, 8));

        // Add movie name
        JLabel nameLabel = new JLabel(m_name[index]);
        nameLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieCard.add(nameLabel);

        // Add movie image
        ImageIcon imageIcon = new ImageIcon(m_path[index]);
        Image image = imageIcon.getImage().getScaledInstance(1200, 400, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBackground(new Color(0, 0, 0));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieCard.add(imageLabel);
        
        // Add movie description
        JTextArea description = new JTextArea(m_description[index]);
       description.setEditable(false);
       description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setBackground(new Color(0, 0, 0));
        description.setForeground(new Color(66, 209, 242));
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        movieCard.add(description);
        
        // Add movie trailer URL button
        JButton trailerButton = new JButton("Watch Trailer");
        trailerButton.setPreferredSize(new Dimension(140, 40));
        trailerButton.setBackground(new Color(255, 0, 0));
        trailerButton.setForeground(Color.WHITE);
        trailerButton.setFont(new Font("Georgia", Font.BOLD, 16));
        trailerButton.setOpaque(true);
        trailerButton.setBorderPainted(true);
        trailerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        trailerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        trailerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(m_trailer[index]));
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Unable to open the trailer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        movieCard.add(trailerButton);

        // Add movie release date
        JLabel dateLabel = new JLabel("Release Date: " + m_date[index] + " Time: " + m_time[index]);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dateLabel.setForeground(Color.white);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieCard.add(dateLabel);

        // Add card to cardPanel
        cardPanel.add(movieCard, m_name[index]);
    }

    // Add the cardPanel to a JScrollPane to enable scrolling
    JScrollPane scrollPane = new JScrollPane(cardPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    frame.add(scrollPane, BorderLayout.CENTER);

    // Navigation panel
    JPanel navigationPanel = new JPanel();
    navigationPanel.setLayout(new FlowLayout());

    JButton prevButton = new JButton("Previous");
    prevButton.setPreferredSize(new Dimension(130, 30));
    prevButton.setBackground(new Color(0, 0, 255));
    prevButton.setFont(new Font("Georgia", Font.BOLD, 20));
    prevButton.setForeground(Color.WHITE);
    prevButton.setOpaque(true);
    prevButton.setBorderPainted(false);
    prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    JButton nextButton = new JButton("Next");
    nextButton.setPreferredSize(new Dimension(130, 30));
    nextButton.setBackground(new Color(0, 255, 0));
    nextButton.setFont(new Font("Georgia", Font.BOLD, 20));
    nextButton.setForeground(Color.WHITE);
    nextButton.setOpaque(true);
    nextButton.setBorderPainted(true);
    nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    navigationPanel.add(prevButton);
    navigationPanel.add(nextButton);
    frame.add(navigationPanel, BorderLayout.SOUTH);

    // Add action listeners for navigation buttons
    prevButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.previous(cardPanel);
        }
    });

    nextButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.next(cardPanel);
        }
    });
    frame.setVisible(true);
}}

class Homepage extends JFrame implements InnerMainProgram {

    JButton b1, b2, b3, search;
    JLabel image;
    JTextField searcht;
    ImageIcon[] images;
    int c = 0;

    String[] usernames = new String[10];
    String[] passwords = new String[10];
    String[] emails = new String[10];
    String[] phoneNumbers = new String[10];
    int userCount = 0;
    int loggedInIndex = -1;
    static String[] securityQuestions = new String[10];
    static String[] securityAnswers = new String[10];

    Homepage() {
        setLayout(null);

        getContentPane().setBackground(Color.black);
        setForeground(Color.white);

        b1 = new JButton("Upcoming Movies");
        b2 = new JButton("Cinemas near me");
        b3 = new JButton("Sign In");

        b1.setBounds(100, 500, 800, 50);
        b2.setBounds(100, 570, 800, 50);
        b3.setBounds(850, 30, 100, 20);

        add(b1);
        add(b2);
        add(b3);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create "More Options" menu
        JMenu moreOptions = new JMenu("More Options");
        menuBar.add(moreOptions);

        // Create menu items
        JMenuItem userAccountInfo = new JMenuItem("User Account Info");
        JMenuItem contactUs = new JMenuItem("Contact Us");
        JMenuItem ratingReviews = new JMenuItem("Rating & Reviews");
        JMenuItem offersAndRewards = new JMenuItem("Offers & Rewards");

        // Add menu items to "More Options" menu
        moreOptions.add(userAccountInfo);
        moreOptions.add(contactUs);
        moreOptions.add(ratingReviews);
        moreOptions.add(offersAndRewards);

        // Menu item action listeners
        userAccountInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedInIndex != -1) {
                    String userDetails = "Username: " + usernames[loggedInIndex] +
                                         "\nEmail: " + emails[loggedInIndex] +
                                         "\nPhone Number: " + phoneNumbers[loggedInIndex];
                    JOptionPane.showMessageDialog(null, userDetails);
                } else {
                    JOptionPane.showMessageDialog(null, "No user is currently logged in.");
                }
            }
        });

        contactUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JDialog contactDialog = new JDialog(Homepage.this, "Contact Us", true);
                contactDialog.setSize(400, 300);
                contactDialog.setLayout(null);
        
                
                JLabel phoneLabel = new JLabel("Phone: +1234567890");
                phoneLabel.setBounds(20, 20, 200, 25);
                contactDialog.add(phoneLabel);
        
                JLabel emailLabel = new JLabel("Email: contact@company.com");
                emailLabel.setBounds(20, 50, 250, 25);
                contactDialog.add(emailLabel);
        
                JLabel messageLabel = new JLabel("Send us a message:");
                messageLabel.setBounds(20, 90, 150, 25);
                contactDialog.add(messageLabel);
        
                JTextArea messageArea = new JTextArea();
                messageArea.setBounds(20, 120, 350, 100);
                contactDialog.add(messageArea);
        
                JButton sendButton = new JButton("Send");
                sendButton.setBounds(150, 230, 80, 30);
                contactDialog.add(sendButton);
        
                sendButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String message = messageArea.getText();
                        if (message.isEmpty()) {
                            JOptionPane.showMessageDialog(contactDialog, "Message cannot be empty!");
                        } else {
                            
                            JOptionPane.showMessageDialog(contactDialog, "Message sent successfully!");
                            contactDialog.dispose();
                        }
                    }
                });
        
               
                contactDialog.setVisible(true);
            }
        });

        ratingReviews.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                JDialog ratingDialog = new JDialog(Homepage.this, "Rating & Reviews", true);
                ratingDialog.setSize(400, 300);
                ratingDialog.setLayout(null);
        
                JLabel reviewLabel = new JLabel("Submit your review:");
                reviewLabel.setBounds(20, 20, 150, 25);
                ratingDialog.add(reviewLabel);
        
                JTextArea reviewArea = new JTextArea();
                reviewArea.setBounds(20, 50, 350, 150);
                ratingDialog.add(reviewArea);
        
                JButton submitButton = new JButton("Submit");
                submitButton.setBounds(150, 210, 80, 30);
                ratingDialog.add(submitButton);
        
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String review = reviewArea.getText();
                        if (review.isEmpty()) {
                            JOptionPane.showMessageDialog(ratingDialog, "Review cannot be empty!");
                        } else {
                          
                            JOptionPane.showMessageDialog(ratingDialog, "Review submitted successfully!");
                            ratingDialog.dispose();
                        }
                    }
                });
        
                ratingDialog.setLocationRelativeTo(Homepage.this);
                ratingDialog.setVisible(true);
            }
        });

        offersAndRewards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create Offers & Rewards dialog
                JDialog offersDialog = new JDialog(Homepage.this, "Offers & Rewards", true);
                offersDialog.setSize(400, 300);
                offersDialog.setLayout(null);
        
                JTextArea offersTextArea = new JTextArea();
                offersTextArea.setBounds(20, 20, 350, 200);
                offersTextArea.setText("Offers & Rewards:\n1. Buy 5 tickets, get 1 free.\n2. Book up to 1000, get 10% off.\n3. First booking, 15% off.\n4. After one booking, earn 5 Rs; collect 100 Rs for use.");
                offersTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
                offersTextArea.setBackground(new Color(240, 240, 240));
                offersTextArea.setEditable(false);
                offersDialog.add(offersTextArea);
        
                JButton closeButton = new JButton("Close");
                closeButton.setBounds(150, 230, 80, 30);
                offersDialog.add(closeButton);
        
               
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        offersDialog.dispose();
                    }
                });
        
                offersDialog.setLocationRelativeTo(Homepage.this);
                offersDialog.setVisible(true);
            }
        });

        JLabel searchL = new JLabel("Search Movies:");
        searchL.setBounds(100, 20, 100, 25);
        add(searchL);
        searchL.setBackground(Color.black);
        searchL.setForeground(Color.white);

        searcht = new JTextField();
        searcht.setBounds(200, 20, 500, 25);
        add(searcht);

        search = new JButton("Search");
        search.setBounds(710, 20, 90, 25);
        add(search);

      

        images = new ImageIcon[5];
        images[0] = new ImageIcon("C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\bloody daddy.jpg");
        images[1] = new ImageIcon("C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\stree2.jpg");
        images[2] = new ImageIcon("C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\badnewz.jpg");
        images[3] = new ImageIcon("C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\kalki.jpg");
        images[4] = new ImageIcon("C:\\Users\\Admin\\Desktop\\Advanced java\\Advanced java\\munjya.jpg");

        image = new JLabel();
        image.setBounds(100, 50, 800, 400);
        image.setIcon(images[c]); // initial image
        add(image);

        // Timer to change images every 2 seconds
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c = (c + 1) % images.length;
                image.setIcon(images[c]);
            }
        });

        timer.start(); // Start timer

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Signin().setVisible(true); // Open Sign In window
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new MovieTheaterApp().setVisible(true);
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                new Movies().upcoming();
                
            }
        });

    }

    class Signin extends JFrame {
        JTextField user;
        JPasswordField pass;
        JButton signIn, create, forgotPassword;
        JTextField securityAnswer; 
    
        Signin() {
            setTitle("Sign In");
            setSize(400, 400);
            setLayout(null);
    
            // Username label and text field
            JLabel L1 = new JLabel("Username:");
            user = new JTextField();
            L1.setBounds(50, 50, 100, 25);
            user.setBounds(170, 50, 150, 25);
            add(L1);
            add(user);
    
            // Password label and password field
            JLabel password = new JLabel("Password:");
            pass = new JPasswordField();
            password.setBounds(50, 100, 100, 25);
            pass.setBounds(170, 100, 150, 25);
            add(password);
            add(pass);
    
            JLabel securityAnswerLabel = new JLabel("Security Answer:");
            securityAnswer = new JTextField();
            securityAnswer.setBounds(50, 150, 270, 25);
            securityAnswer.setVisible(false); 
            add(securityAnswerLabel);
            add(securityAnswer);
    
            // Sign In button
            signIn = new JButton("Sign In");
            signIn.setBounds(50, 200, 120, 30);
            add(signIn);
    
            // Create Account button
            create = new JButton("Create Account");
            create.setBounds(200, 200, 120, 30);
            add(create);
    
           
            forgotPassword = new JButton("Forgot Password?");
            forgotPassword.setBounds(100, 250, 200, 30);
            forgotPassword.setVisible(false); // Set initially invisible
            add(forgotPassword);
    
            signIn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = user.getText();
                    String password = String.valueOf(pass.getPassword());
    
                    boolean valid = false;
    
                    
                    for (int i = 0; i < userCount; i++) {
                        if (usernames[i].equals(username) && passwords[i].equals(password)) {
                            valid = true;
                            loggedInIndex = i; 
                            break;
                        }
                    }
    
                    if (valid) {
                        JOptionPane.showMessageDialog(null, "Sign In Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                        forgotPassword.setVisible(true); // Show the "Forgot Password" button after failure
                    }
                }
            });
    
           
            forgotPassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = user.getText();
    
                    boolean found = false;
    
                   
                    for (int i = 0; i < userCount; i++) {
                        if (usernames[i].equals(username)) {
                            found = true;
                            String question = securityQuestions[i];
                            String answer = securityAnswers[i];
    
                            String userAnswer = JOptionPane.showInputDialog(null, "Security Question: " + question);
    
                            if (userAnswer != null && userAnswer.equals(answer)) {
                                JOptionPane.showMessageDialog(null, "Security Answer Correct! You can now reset your password.");
                                // Implement password reset functionality here
                            } else {
                                JOptionPane.showMessageDialog(null, "Incorrect Security Answer!");
                            }
                            break;
                        }
                    }
    
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Username not found!");
                    }
                }
            });
    
           
            create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CreateAccount().setVisible(true); 
                    dispose(); 
                }
            });
        }
    }
    

    class CreateAccount extends JFrame {
        JTextField user, email, phone, securityAnswer;
        JPasswordField pass, confirmPass;
        JButton create, signIn;
        JComboBox<String> securityQuestion; // For selecting a security question
    
        CreateAccount() {
            setTitle("Create Account");
            setSize(400, 500); // Increase size to fit new fields
            setLayout(null);
    
            
            JLabel L1 = new JLabel("Username:");
            user = new JTextField();
            L1.setBounds(50, 50, 100, 25);
            user.setBounds(170, 50, 150, 25);
            add(L1);
            add(user);

           
            JLabel emailLabel = new JLabel("Email:");
            email = new JTextField();
            emailLabel.setBounds(50, 100, 100, 25);
            email.setBounds(170, 100, 150, 25);
            add(emailLabel);
            add(email);
    
            JLabel phoneLabel = new JLabel("Phone Number:");
            phone = new JTextField();
            phoneLabel.setBounds(50, 150, 100, 25);
            phone.setBounds(170, 150, 150, 25);
            add(phoneLabel);
            add(phone);
       
            JLabel password = new JLabel("Password:");
            pass = new JPasswordField();
            password.setBounds(50, 200, 100, 25);
            pass.setBounds(170, 200, 150, 25);
            add(password);
            add(pass);

            JLabel confirmPassword = new JLabel("Confirm Password:");
            confirmPass = new JPasswordField();
            confirmPassword.setBounds(50, 250, 120, 25);
            confirmPass.setBounds(170, 250, 150, 25);
            add(confirmPassword);
            add(confirmPass);
    
            
            JLabel securityQuestionLabel = new JLabel("Security Question:");
            securityQuestion = new JComboBox<>(new String[] {
                "What is your pet's name?",
                "What was the name of your first school?"
            });
            securityQuestion.setBounds(50, 300, 270, 25);
            add(securityQuestionLabel);
            add(securityQuestion);
    
            JLabel securityAnswerLabel = new JLabel("Security Answer:");
            securityAnswer = new JTextField();
            securityAnswer.setBounds(50, 350, 270, 25);
            add(securityAnswerLabel);
            add(securityAnswer);
    
            create = new JButton("Create Account");
            create.setBounds(50, 400, 120, 30);
            add(create);
    
          
            signIn = new JButton("Sign In");
            signIn.setBounds(200, 400, 120, 30);
            add(signIn);
    
           
            create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = user.getText();
                    String emailInput = email.getText();
                    String phoneInput = phone.getText();
                    String password = String.valueOf(pass.getPassword());
                    String confirmPassword = String.valueOf(confirmPass.getPassword());
                    String question = (String) securityQuestion.getSelectedItem();
                    String answer = securityAnswer.getText();
    
                   
                    if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "Passwords do not match!");
                    } else if (userCount >= usernames.length) {
                        JOptionPane.showMessageDialog(null, "User limit reached!");
                    } else {
                      
                        usernames[userCount] = username;
                        passwords[userCount] = password;
                        emails[userCount] = emailInput;
                        phoneNumbers[userCount] = phoneInput;
                        securityQuestions[userCount] = question;
                        securityAnswers[userCount] = answer;
                        userCount++;
                        JOptionPane.showMessageDialog(null, "Account Created Successfully!");
                    }
                }
            });
    
           
            signIn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Signin().setVisible(true); // Open Sign In window
                    dispose();
                }
            });
        }
    }
    
    public static void main(String[] args) {
        Homepage h1 = new Homepage();
         h1.setVisible(true);
        h1.setTitle("Homepage");
        h1.setSize(1000, 1000);
    }
}

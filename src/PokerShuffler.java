import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerShuffler extends JFrame {
    private JPanel deckPanel;
    private List<JLabel> cardLabels;
    private final int TOTAL_CARDS = 52;

    public PokerShuffler() { //
        setTitle("Card Randomizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        deckPanel = new JPanel(new GridLayout(4, 13, 10, 10)); //GridLayout for the 4 rows for suits, 13 columns for ranks.
        deckPanel.setBackground(new Color(34, 139, 34)); // poker table color
        deckPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //so the cards are not hugging the perimeter

        cardLabels = new ArrayList<>();
        loadCards();
        displayCards();

        //shuffle button
        JButton shuffleBtn = new JButton("Shuffle Deck");
        shuffleBtn.setPreferredSize(new Dimension(200, 60));
        shuffleBtn.setFocusPainted(false);

        shuffleBtn.addActionListener(e -> shuffleAndDisplay()); // when clicked, shuffle list and refreashes UI

        add(new JScrollPane(deckPanel), BorderLayout.CENTER); //shows the "poker table"
        add(shuffleBtn, BorderLayout.SOUTH); //shuffle button at the bottom

        setVisible(true);
    }
    //load card function from images files
    private void loadCards() {
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                String fileName = "images/" + suit + "_" + rank + ".png"; // seeks from image files, finds suit string followed by the rank "images/clubs_2.png", "images/hearts_Q.png"

                ImageIcon icon = new ImageIcon(fileName);

                if (icon.getImageLoadStatus() == MediaTracker.ERRORED) { //exception to check if the png is there or not. to make sure that all pngs are there.
                    System.out.println("Could not find: " + fileName);
                    continue;
                }

                Image img = icon.getImage().getScaledInstance(80, 115, Image.SCALE_SMOOTH); //rescale
                cardLabels.add(new JLabel(new ImageIcon(img)));
            }
        }
    }

    private void displayCards() { //display card function
        deckPanel.removeAll();
        for (JLabel label : cardLabels) {
            deckPanel.add(label);
        }
        deckPanel.revalidate();
        deckPanel.repaint();
    }

    private void shuffleAndDisplay() { //shuffle and display new card layout
        Collections.shuffle(cardLabels);
        displayCards();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PokerShuffler::new); //run GUI
    }
}
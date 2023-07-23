import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {
    private int randomNumber;
    private int attempts;
    private JLabel messageLabel;
    private JTextField inputField;
    private JButton guessButton;
System.out.println("Just to see if this works!");

    public GuessingGameGUI() {
        // Initialize the game
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;

        setTitle("Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        messageLabel = new JLabel("Guess a number between 1 and 100:");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.PLAIN, 18));
        guessButton.addActionListener(new GuessButtonListener());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.add(messageLabel);
        contentPanel.add(inputField);
        contentPanel.add(guessButton);

        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(inputField.getText());
                attempts++;

                if (guess == randomNumber) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " + attempts + " attempts.");
                    resetGame();
                } else if (guess < randomNumber) {
                    messageLabel.setText("Try a higher number. Attempts: " + attempts);
                } else {
                    messageLabel.setText("Try a lower number. Attempts: " + attempts);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        }
    }

    private void resetGame() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        messageLabel.setText("Guess a number between 1 and 100:");
        inputField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessingGameGUI());
    }
}

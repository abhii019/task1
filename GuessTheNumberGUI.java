
    

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI {
    private static int targetNumber;
    private static int attempts;
    private static int maxAttempts = 5;

    public static void main(String[] args) {
        generateTargetNumber();

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void generateTargetNumber() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        attempts = 0;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:");
        JTextField guessField = new JTextField(10);
        JButton guessButton = new JButton("Submit Guess");

        JLabel resultLabel = new JLabel("");
        JLabel attemptsLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = guessField.getText();

                if (!userInput.isEmpty()) {
                    int userGuess = Integer.parseInt(userInput);
                    attempts++;

                    if (userGuess == targetNumber) {
                        resultLabel.setText("Congratulations! You guessed the number!");
                        resetGame();
                    } else {
                        if (userGuess < targetNumber) {
                            resultLabel.setText("Try again. The number is higher.");
                        } else {
                            resultLabel.setText("Try again. The number is lower.");
                        }

                        attemptsLabel.setText("Attempts: " + attempts);

                        if (attempts == maxAttempts) {
                            resultLabel.setText("Sorry, you've reached the maximum number of attempts. The number was: " + targetNumber);
                            resetGame();
                        }
                    }
                } else {
                    resultLabel.setText("Please enter a valid number.");
                }

                guessField.setText(""); // Clear the text field after each guess
            }
        });

        frame.add(instructionLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(resultLabel);
        frame.add(attemptsLabel);

        frame.setVisible(true);
    }

    private static void resetGame() {
        generateTargetNumber();
        attempts = 0;
    }
}



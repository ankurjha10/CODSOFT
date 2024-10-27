package atmmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// Class to represent the bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}

// Panel to display background image
class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    public BackgroundPanel() {
        try {
            // Set your image path here (for local image)
            backgroundImage = ImageIO.read(new File("path/to/your/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

// Main ATM class with GUI
public class AtmMachine extends JFrame implements ActionListener {
    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton checkBalanceButton, depositButton, withdrawButton, exitButton;

    public AtmMachine(BankAccount account) {
        this.account = account;

        // Set up JFrame
        setTitle("ATM Machine");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create BackgroundPanel
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridLayout(6, 1));

        // Create components
        balanceLabel = new JLabel("Welcome to the ATM!", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 22));
        balanceLabel.setForeground(Color.BLACK);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setBackground(new Color(240, 240, 240));

        checkBalanceButton = createButton("Check Balance");
        depositButton = createButton("Deposit");
        withdrawButton = createButton("Withdraw");
        exitButton = createButton("Exit");

        // Add ActionListener to buttons
        checkBalanceButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add components to background panel
        backgroundPanel.add(balanceLabel);
        backgroundPanel.add(amountField);
        backgroundPanel.add(checkBalanceButton);
        backgroundPanel.add(depositButton);
        backgroundPanel.add(withdrawButton);
        backgroundPanel.add(exitButton);

        // Add background panel to frame
        add(backgroundPanel);
    }

    // Method to create buttons with consistent styling
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 153, 51)); // Green color for buttons
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBalanceButton) {
            balanceLabel.setText("Current Balance: ₹" + account.getBalance());
        } else if (e.getSource() == depositButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                balanceLabel.setText("₹" + amount + " deposited successfully!");
            } catch (NumberFormatException ex) {
                balanceLabel.setText("Invalid input. Please enter a number.");
            }
        } else if (e.getSource() == withdrawButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= account.getBalance()) {
                    account.withdraw(amount);
                    balanceLabel.setText("₹" + amount + " withdrawn successfully!");
                } else {
                    balanceLabel.setText("Insufficient balance.");
                }
            } catch (NumberFormatException ex) {
                balanceLabel.setText("Invalid input. Please enter a number.");
            }
        } else if (e.getSource() == exitButton) {
            JOptionPane.showMessageDialog(this, "Thank you for using the ATM!");
            System.exit(0);
        }
        amountField.setText("");
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000); // Initial balance ₹5000
        AtmMachine atm = new AtmMachine(userAccount);
        atm.setVisible(true);
    }
}

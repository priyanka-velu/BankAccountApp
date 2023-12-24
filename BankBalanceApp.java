import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankBalanceApp {
    private double balance = 0.0;
    private JLabel balanceLabel;

    public static void main(String[] args) {
        BankBalanceApp app = new BankBalanceApp();
        app.frame();
    }

    private void frame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setTitle("Bank Balance Application");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        components(panel);
        frame.setVisible(true);
    }

    private void components(JPanel panel) {
        panel.setLayout(null);

        JLabel startLabel = new JLabel("Enter Starting Balance:");
        startLabel.setBounds(10, 20, 150, 30);
        panel.add(startLabel);

        JTextField startField = new JTextField(30);
        startField.setBounds(160, 20, 120, 30);
        panel.add(startField);

        JButton setBalanceButton = new JButton("Set Balance");
        setBalanceButton.setBounds(10, 50, 150, 30);
        panel.add(setBalanceButton);

        balanceLabel = new JLabel("Current Balance: ");
        balanceLabel.setBounds(10, 80, 200, 30);
        panel.add(balanceLabel);

        JLabel optionLabel = new JLabel("Select an Option: ");
        optionLabel.setBounds(10, 110, 200, 30);
        panel.add(optionLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 140, 80, 30);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 170, 100, 30);
        panel.add(withdrawButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(10, 200, 80, 30);
        panel.add(exitButton);

        setBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    balance = Double.parseDouble(startField.getText());
                    updateBalanceLabel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid balance input. Please enter a valid number.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double depositAmount = Double.parseDouble(JOptionPane.showInputDialog(panel, "Current Balance: $" + balance + ". Enter deposit amount:"));
                    balance += depositAmount;
                    updateBalanceLabel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double withdrawalAmount = Double.parseDouble(JOptionPane.showInputDialog(panel, "Current Balance: $" + balance + ". Enter withdrawal amount:"));
                    if (withdrawalAmount > balance) {
                        JOptionPane.showMessageDialog(panel, "Insufficient funds.");
                    } else {
                        balance -= withdrawalAmount;
                        updateBalanceLabel();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a valid number.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "Final Balance: $" + balance);
                System.exit(0);
            }
        });
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Current Balance: $" + balance);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {

    private JLabel displayLabel; // A label to display numbers and results
    private String currentInput; // Variable to store the current user input
    private String firstOperand;  // Variable to store the first operand for calculations
    private String operator;      // Variable to store the operator (+, -, *, /)

    public UI() {
        initializeUI();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeUI() {
        setSize(400, 600);
        setLayout(new BorderLayout());

        // Display label
        displayLabel = new JLabel("0", JLabel.RIGHT);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(displayLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        add(buttonPanel, BorderLayout.CENTER);

        // Initialize variables
        currentInput = "0";
        firstOperand = null;
        operator = null;

        // Set up action listeners for buttons
        setupButton("7", buttonPanel);
        setupButton("8", buttonPanel);
        setupButton("9", buttonPanel);
        setupOperator("/", buttonPanel);

        setupButton("4", buttonPanel);
        setupButton("5", buttonPanel);
        setupButton("6", buttonPanel);
        setupOperator("*", buttonPanel);

        setupButton("1", buttonPanel);
        setupButton("2", buttonPanel);
        setupButton("3", buttonPanel);
        setupOperator("-", buttonPanel);

        setupButton("0", buttonPanel);
        setupClearButton("C", buttonPanel);
        setupEqualsButton("=", buttonPanel);
        setupOperator("+", buttonPanel);
    }

    // Helper method to set up numeric buttons
    private void setupButton(String label, JPanel panel) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(this);
        panel.add(button);
    }

    // Helper method to set up operator buttons (+, -, *, /)
    private void setupOperator(String label, JPanel panel) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(this);
        panel.add(button);
    }

    // Helper method to set up the equals button
    private void setupEqualsButton(String label, JPanel panel) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(this);
        panel.add(button);
    }

    // Helper method to set up the clear button (C)
    private void setupClearButton(String label, JPanel panel) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        // Handle numeric input
        if (Character.isDigit(buttonText.charAt(0))) {
            handleNumericInput(buttonText);
        } else if ("+-*/".contains(buttonText)) {
            handleOperatorInput(buttonText);
        } else if ("=".equals(buttonText)) {
            handleEqualsButton();
        } else if ("C".equals(buttonText)) {
            handleClearButton();
        }

        // Update the display label
        displayLabel.setText(currentInput);
    }

    private void handleNumericInput(String digit) {
        if ("0".equals(currentInput) || currentInput.equals("Error")) {
            currentInput = digit;
        } else {
            currentInput += digit;
        }
    }

    // Method to handle operator input
    private void handleOperatorInput(String op) {
        if ("0".equals(currentInput) || currentInput.equals("Error")) {
            // Set first operand to the current input
            firstOperand = currentInput;
        } else if (firstOperand == null) {
            // If first operand is not set, set it to the current input
            firstOperand = currentInput;
        } else {
            // If both operands and operator are already set, perform calculation
            int result = performCalculation();
            currentInput = String.valueOf(result);
            firstOperand = currentInput;
        }

        operator = op;
    }



    private void handleEqualsButton() {
        if (firstOperand != null && operator != null) {
            int result = performCalculation();
            currentInput = String.valueOf(result);
            firstOperand = null;
            operator = null;
        }
    }

    private void handleClearButton() {
        currentInput = "0";
        firstOperand = null;
        operator = null;
    }

    private int performCalculation() {
        int num1 = Integer.parseInt(firstOperand);
        int num2 = Integer.parseInt(currentInput);

        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return 0; // Handle division by zero
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new UI();
    }
}

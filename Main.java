import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton;
    private double num1, num2, result;
    private char operator;

    public Main() {
        frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 30, 340, 50);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + e.getActionCommand());
                }
            });
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");

        functionButtons = new JButton[]{addButton, subButton, mulButton, divButton, eqButton, clrButton};
        for (JButton button : functionButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if ((command.charAt(0) == '+' || command.charAt(0) == '-' ||
                            command.charAt(0) == '*' || command.charAt(0) == '/') && !textField.getText().isEmpty()) {
                        num1 = Double.parseDouble(textField.getText());
                        operator = command.charAt(0);
                        textField.setText("");
                    } else if (command.charAt(0) == '=') {
                        if (!textField.getText().isEmpty()) {
                            num2 = Double.parseDouble(textField.getText());
                            switch (operator) {
                                case '+':
                                    result = num1 + num2;
                                    break;
                                case '-':
                                    result = num1 - num2;
                                    break;
                                case '*':
                                    result = num1 * num2;
                                    break;
                                case '/':
                                    if (num2 != 0) {
                                        result = num1 / num2;
                                    } else {
                                        textField.setText("Ошибка");
                                        return;
                                    }
                                    break;
                            }
                            textField.setText(String.valueOf(result));
                        }
                    } else if (command.charAt(0) == 'C') {
                        textField.setText("");
                        num1 = num2 = result = 0;
                    }
                }
            });
        }

        panel = new JPanel();
        panel.setBounds(30, 100, 340, 250);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(clrButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);

        frame.add(textField);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

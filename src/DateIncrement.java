import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;

public class DateIncrement extends JFrame {
    JButton calculate;
    JButton today;
    ActionListener listenerLabel;
    ActionListener listenerToday;
    JPanel pnl1;
    JPanel pnl2;
    JPanel pnl3;
    JTextField input;
    JTextArea result;

    public DateIncrement() {
        setLocation(500, 300);
        createGUI();
        setResizable(false);
        pack();
        setSize(180, 400);
    }

    private void createGUI() {
        Dimension dimensionButton = new Dimension(120, 20);
        Dimension dimensionPnl1 = new Dimension(120, 60);
        pnl1 = new JPanel();
        pnl1.setLayout(new GridLayout(2, 1));
        pnl2 = new JPanel();
        pnl2.setLayout(new FlowLayout());
        pnl3 = new JPanel();
        pnl3.setLayout(new FlowLayout());
        calculate = new JButton();
        calculate.setText("Посчитать");
        calculate.setFont(new Font("Serif", Font.BOLD, 16));
        today = new JButton();
        today.setText("Сегодня");

        pnl1.setPreferredSize(dimensionPnl1);
        pnl1.setMaximumSize(dimensionPnl1);
        calculate.setPreferredSize(dimensionButton);
        calculate.setMaximumSize(dimensionButton);
        input = new JTextField();
        input.setFont(new Font("Serif", Font.BOLD, 16));
        result = new JTextArea();
        result.setEditable(false);
        result.setFont(new Font("Serif", Font.BOLD, 16));
        Dimension dimension = new Dimension(80, 30);
        input.setMaximumSize(dimension);
        input.setPreferredSize(dimension);

        listenerLabel = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                result.setText(calculateDate(input.getText()));
                }catch (DateTimeParseException ex){
                    JOptionPane.showMessageDialog(pnl3,
                            "\tНеверный ввод даты\n " +
                                    "Введите дату в формате ДД.ММ.ГГГГ",
                            "Ошибка!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        calculate.addActionListener(listenerLabel);

        listenerToday = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                result.setText(calculateDate("today"));
                input.setText("Сегодня");
            }
        };
        today.addActionListener(listenerToday);

        pnl1.add(calculate);
        pnl1.add(today);
        pnl2.add(input);
        pnl3.add(result);
        Container container1 = getContentPane();
        container1.add(pnl2);
        container1.add(pnl1);
        container1.add(pnl3);
        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
    }

    private String calculateDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date;
        date = input.equals("today") ? LocalDate.now() : LocalDate.parse(input.trim(), formatter);
        StringBuilder resultConcatDate = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            date = date.plusDays(28);
            resultConcatDate.append(date).append("\n");
        }
        return resultConcatDate.toString().trim();
    }

    public static void main(String[] args) {
        DateIncrement frm = new DateIncrement();
        frm.setName("Расчёт дат");
        frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
}
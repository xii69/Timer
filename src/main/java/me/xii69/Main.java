package me.xii69;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main extends JFrame implements ActionListener {
    private final Timer timer;
    private final JLabel timerLabel;
    private final JButton startButton, pauseButton, resumeButton, resetButton;
    private int seconds = 0;
    private boolean isPaused = false;

    public Main() {
        setTitle("Timer");
        setSize(325, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 69));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timerLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        buttonPanel.add(startButton);

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        buttonPanel.add(pauseButton);

        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(this);
        buttonPanel.add(resumeButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> {
            if (!isPaused) {
                seconds++;
                updateTimerLabel();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            timer.start();
            startButton.setEnabled(false);
        } else if (e.getSource() == pauseButton) {
            isPaused = true;
        } else if (e.getSource() == resumeButton) {
            isPaused = false;
        } else if (e.getSource() == resetButton) {
            timer.stop();
            seconds = 0;
            updateTimerLabel();
            startButton.setEnabled(true);
            isPaused = false;
        }
    }

    private void updateTimerLabel() {
        DecimalFormat df = new DecimalFormat("00");
        timerLabel.setText(df.format(seconds / 3600) + ":" + df.format((seconds % 3600) / 60) + ":" + df.format(seconds % 60));
    }
}

package colorSliders;

import javax.swing.*;
import java.awt.*;

public class ColorSliders extends JFrame {
    private ColorSliders() {
        super("Color Sliders");
        int scWid = Toolkit.getDefaultToolkit().getScreenSize().width,
                scHei = Toolkit.getDefaultToolkit().getScreenSize().height,
                wWid = Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                wHei = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
        this.setBounds(new Rectangle((scWid - wWid) / 2, (scHei - wHei) / 2, wWid, wHei));

        initComponents();

        this.setDefaultCloseOperation(3);
    }

    private void initComponents() {

        this.getContentPane().add(sliderPanel, BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.getContentPane().add(colorValuePanel, BorderLayout.SOUTH);
        panel.setBackground(Color.BLACK);
        sliderPanel.setLayout(new GridLayout(3, 1));
        colorValuePanel.setLayout(new GridLayout(1, 3));
        colorValuePanel.add(redValue);
        colorValuePanel.add(greenValue);
        colorValuePanel.add(blueValue);

        red.addChangeListener((e -> {
            redValue.setText(red.sliderName + ": " + ((JSlider) e.getSource()).getValue());
            panel.setBackground(new Color(((JSlider) e.getSource()).getValue(), green.getValue(), blue.getValue()));
        }));

        green.addChangeListener((e -> {
            greenValue.setText(green.sliderName + ": " + ((JSlider) e.getSource()).getValue());
            panel.setBackground(new Color(red.getValue(), ((JSlider) e.getSource()).getValue(), blue.getValue()));
        }));

        blue.addChangeListener((e -> {
            blueValue.setText(blue.sliderName + ": " + ((JSlider) e.getSource()).getValue());
            panel.setBackground(new Color(red.getValue(), green.getValue(), ((JSlider) e.getSource()).getValue()));
        }));

        redValue.setEditable(false);
    }

    private class ColorSlider extends JSlider {
        String sliderName;

        ColorSlider(String name) {
            super(JSlider.HORIZONTAL, 0, 255, 0);
            this.sliderName = name;
            this.setMajorTickSpacing(15);
            this.setPaintTicks(true);
            this.setPaintLabels(true);
            this.setSnapToTicks(false);
            sliderPanel.add(this);
        }
    }

    private JPanel colorValuePanel = new JPanel();
    private JPanel sliderPanel = new JPanel();
    private JPanel panel = new JPanel();
    private ColorSlider red = new ColorSlider("Red");
    private ColorSlider green = new ColorSlider("Green");
    private ColorSlider blue = new ColorSlider("Blue");
    private JTextField redValue = new JTextField("Red: " + red.getValue(), 10);
    private JTextField greenValue = new JTextField("Green: " + green.getValue(), 10);
    private JTextField blueValue = new JTextField("Blue: " + blue.getValue(), 10);

    public static void main(String[] args) {
        new ColorSliders().setVisible(true);
    }
}
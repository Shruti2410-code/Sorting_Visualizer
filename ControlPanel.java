import java.awt.*;
import javax.swing.*;

public class ControlPanel extends JPanel {

    private VisualizerPanel visualizer;

    public ControlPanel(VisualizerPanel visualizer) {
        this.visualizer = visualizer;

        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;

        // Row 0, Col 0: Randomize button
        JButton randomizeBtn = new JButton("Randomize Bars");
        styleButton(randomizeBtn, new Color(0x2563EB));
        randomizeBtn.addActionListener(e -> visualizer.randomizeArray());
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(randomizeBtn, c);

        // Row 0, Col 1: Array Size label
        JLabel sizeLabel = new JLabel("Array Size:");
        sizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0;
        add(sizeLabel, c);

        // Row 0, Col 2: Array Size slider
        JSlider sizeSlider = new JSlider(5, 150, 25);
        styleSlider(sizeSlider);
        sizeSlider.setMajorTickSpacing(145); // only show min/max labels
        sizeSlider.setMinorTickSpacing(5);
        sizeSlider.setPaintLabels(true);
        sizeSlider.addChangeListener(e -> {
            if (!sizeSlider.getValueIsAdjusting()) {
                int newSize = sizeSlider.getValue();
                visualizer.updateArraySize(newSize);
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1.0;
        add(sizeSlider, c);

        // Row 1, Col 1: Speed label
        JLabel speedLabel = new JLabel("Speed:");
        speedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0;
        add(speedLabel, c);

        // Row 1, Col 2: Speed slider
        JSlider speedSlider = new JSlider(5, 500, 100);
        styleSlider(speedSlider);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(25);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(e -> visualizer.setDelay(speedSlider.getValue()));
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1.0;
        add(speedSlider, c);

        // Row 2, Col 1: Algorithm label
        JLabel algoLabel = new JLabel("Algorithm:");
        algoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0;
        add(algoLabel, c);

        // Row 2, Col 2: Algorithm dropdown
        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort"};
        JComboBox<String> algoDropdown = new JComboBox<>(algorithms);
        algoDropdown.setPreferredSize(new Dimension(160, 28));
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 1.0;
        add(algoDropdown, c);

        // Row 2, Col 0: Start Sorting button (aligned under Randomize)
        JButton sortBtn = new JButton("Start Sorting");
        styleButton(sortBtn, new Color(0x1E3A8A));
        sortBtn.addActionListener(e -> {
            String selectedAlgo = (String) algoDropdown.getSelectedItem();
            // run sorting in background thread
            new Thread(() -> {
                try {
                    switch (selectedAlgo) {
                        case "Bubble Sort":
                            visualizer.bubbleSort();
                            break;
                        case "Selection Sort":
                            visualizer.selectionSort();
                            break;
                        case "Insertion Sort":
                            visualizer.insertionSort();
                            break;
                        case "Merge Sort":
                            visualizer.mergeSort(0, visualizer.getArray().length - 1);
                            break;
                    }
                    // after sorting, color all green (visual confirmation)
                    Color[] colors = visualizer.getColors();
                    for (int i = 0; i < colors.length; i++) colors[i] = Color.GREEN;
                    visualizer.repaint();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        add(sortBtn, c);
    }

    // button styling helper
    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(140, 34));
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        // simple hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
    }

    // slider styling helper
    private void styleSlider(JSlider slider) {
        slider.setPreferredSize(new Dimension(220, 42));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }
}

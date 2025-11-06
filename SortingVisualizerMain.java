import java.awt.*;
import javax.swing.*;

public class SortingVisualizerMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sorting Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 650);
            frame.setLayout(new BorderLayout());

            // 🔹 Create main visualizer panel
            VisualizerPanel visualizer = new VisualizerPanel(60);
            frame.add(visualizer, BorderLayout.CENTER);

            // 🔹 Add control panel at top
            ControlPanel controls = new ControlPanel(visualizer);
            frame.add(controls, BorderLayout.NORTH);

            // center on screen
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

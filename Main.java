import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Create the main application window
        JFrame frame = new JFrame("Sorting Visualizer");
        frame.setSize(900, 700); // slightly wider for better layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());

        //Create the visualizer panel (where sorting happens)
        VisualizerPanel visualizer = new VisualizerPanel(50); // 50 bars looks balanced
        frame.add(visualizer, BorderLayout.CENTER);

        //Create control panel (buttons, dropdown, etc.)
        ControlPanel controls = new ControlPanel(visualizer);
        frame.add(controls, BorderLayout.SOUTH);

        //Optional heading label at the top
        JLabel title = new JLabel("Sorting Visualizer", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204)); // same theme blue
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);

        //Show the frame
        frame.setVisible(true);
    }
}

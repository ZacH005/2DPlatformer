import javax.swing.*;
import java.awt.*;

public class PlatformerGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Platformer");
        JLabel playerInfoLabel = new JLabel("Player Info");
        playerInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        PlatformerCanvas gameCanvas = new PlatformerCanvas(playerInfoLabel);

        frame.setLayout(new BorderLayout());
        frame.add(gameCanvas, BorderLayout.CENTER);
        frame.add(playerInfoLabel, BorderLayout.SOUTH);
        frame.setSize(1400, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gameCanvas.requestFocusInWindow();  // Ensures that the canvas has focus for key events
    }
}

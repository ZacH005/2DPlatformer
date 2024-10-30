import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class PlatformerCanvas extends JPanel implements ActionListener, KeyListener {
    private int Playerx = 50, Playery = 250;           // Player's position
    private int width = 50, height = 100;              // Player's dimensions
    private int velocityX = 0;                         // X-axis velocity
    private int velocityY = 0;                         // Y-axis velocity
    private boolean isJumping = false;                 // To check if the player is in the air

    private final int GRAVITY = 1;                     // Gravity strength
    private final int JUMP_STRENGTH = -15;             // Jump velocity
    private final int MOVE_SPEED = 10;                 // Speed of movement

    Color purple = new Color(128, 0, 123);
    private JLabel playerInfoLabel;
    private List<Platform> platforms;

    public PlatformerCanvas(JLabel playerInfoLabel) {
        this.playerInfoLabel = playerInfoLabel;

        // Initialize platforms
        platforms = new ArrayList<>();
        platforms.add(new Platform(0, 800, 3000, 600));   // Ground level
        platforms.add(new Platform(800, 600, 50, 20));   // Elevated platform
        platforms.add(new Platform(400, 780, 200, 20));   // Another elevated platform
        platforms.add(new Platform(700, 500, 50, 20));

        Timer timer = new Timer(20, this);  // Game loop timer
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(purple);
        g.fillRect(Playerx, Playery, width, height);  // Draw the player

        // Draw platforms
        g.setColor(Color.green);
        for (Platform platform : platforms) {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Apply gravity if the player is above ground or not on a platform
        if (!isOnGround()) {
            velocityY += GRAVITY;
            isJumping = true;
        } else {
//            velocityY = 0;
            isJumping = false;
        }

        for (Platform platform : platforms) {
//            if (Playerx + width == platform.x && Playery + height == platform.y + platform.height) {
//                //do not move to the right
//                break;
//            } else if (Playerx == platform.x + platform.width && Playery + height == platform.y + platform.height)  {
//                //do not move to the left
//
//                break;
//            }
            // Vectors trying to find if point of platform lies on player side. Using right side of player to find if the top left point on platform is on the line
//            if (distance(Playerx+width, platform.x, Playery, platform.y) + distance(platform.x, Playerx+width, platform.y, Playery+height) == distance(Playerx+width, Playerx+width,Playery, Playery+height))
//                velocityX = 0;
//            if (distance(Playerx, platform.x+ platform.width, Playery, platform.y) + distance(platform.x+ platform.width, Playerx, platform.y, Playery+height) == distance(Playerx, Playerx,Playery, Playery+height))
//                velocityX = 0;
//            if (Playerx + width > platform.x && Playerx < platform.x + platform.width && Playery >= platform.y + platform.height)   {
//                Playery = platform.y + platform.height;
//            }

            // Check if player is colliding with the left side of the platform
            if (Playerx + width > platform.x && Playerx < platform.x &&
                    Playery + height > platform.y && Playery < platform.y + platform.height) {
                Playerx = platform.x - width - velocityX; // position the player on the left side of the platform
            }

            // Check if player is colliding with the right side of the platform
            if (Playerx < platform.x + platform.width && Playerx + width > platform.x + platform.width &&
                    Playery + height > platform.y && Playery < platform.y + platform.height) {
                Playerx = platform.x + platform.width - velocityX; // position the player on the right side of the platform
            }

            // Check if player is colliding with the bottom of the platform
            if (Playery < platform.y + platform.height && Playery + height > platform.y + platform.height &&
                    Playerx + width > platform.x && Playerx < platform.x + platform.width) {
                Playery = platform.y + platform.height + velocityY; // position the player below the platform
            }
        }

        // Player Movement based on Velocity
        Playerx += velocityX;
        Playery += velocityY;

        // Keep the player within screen bounds
        if (Playerx < 0) Playerx = 0;
        if (Playerx + width > getWidth()) Playerx = getWidth() - width;

        // Update player information display
        playerInfoLabel.setText("Position: (" + Playerx + ", " + Playery + ") | X Velocity: " + velocityX + " | Y Velocity: " + velocityY + " | Jumping: " + isJumping + " | On Ground: " + isOnGround());

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            velocityX = -MOVE_SPEED;
        } else if (key == KeyEvent.VK_RIGHT) {
            velocityX = MOVE_SPEED;
        } else if (key == KeyEvent.VK_UP && !isJumping) {
            isJumping = true;
            velocityY = JUMP_STRENGTH; // Jump if on the ground or platform
        }
    }

    public double distance(int x1, int x2, int y1, int y2)   {

        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            velocityX = 0; // Stop horizontal movement when arrow key is released
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Helper method to check if the player is on any platform
    private boolean isOnGround() {
        for (Platform platform : platforms) {
            if (Playerx + width > platform.x && Playerx < platform.x + platform.width && Playery + height >= platform.y && Playery + height <= platform.y + platform.height) {
                Playery = platform.y - height;  // Position player on top of the platform
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Platformer");
        JLabel playerInfoLabel = new JLabel("playerinfo");
        playerInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        PlatformerCanvas game = new PlatformerCanvas(playerInfoLabel);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.add(playerInfoLabel, BorderLayout.SOUTH);
        frame.setSize(1400, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

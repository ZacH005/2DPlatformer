import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlatformerCanvas extends JPanel implements ActionListener {
    private Player player;
    private List<Platform> platforms;
    private CollisionManager collisionManager;
    private JLabel playerInfoLabel;

    public PlatformerCanvas(JLabel playerInfoLabel) {
        this.playerInfoLabel = playerInfoLabel;
        this.player = new Player(50, 250, 50, 100); // Set player initial position and size
        this.platforms = new ArrayList<>();
        this.collisionManager = new CollisionManager(player, platforms);

        // Initialize platforms
        platforms.add(new Platform(0, 800, 3000, 600));
        platforms.add(new Platform(800, 600, 50, 20));
        platforms.add(new Platform(400, 780, 200, 20));
        platforms.add(new Platform(700, 500, 50, 20));

        Timer timer = new Timer(20, this);
        timer.start();

        GameController controller = new GameController(player);
        addKeyListener(controller);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        g.setColor(Color.green);
        for (Platform platform : platforms) {
            platform.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.applyGravity();
        collisionManager.checkCollisions();
        player.updatePosition();

        playerInfoLabel.setText(player.getStatusText(collisionManager.isOnGround()));
        repaint();
    }
}

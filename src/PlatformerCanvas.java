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
        // just initializing player and making sure platforms array exists doofus
        this.player = new Player(50, 250, 50, 100);
        this.platforms = new ArrayList<>();
        this.collisionManager = new CollisionManager(player, platforms);

        platforms.add(new Platform(0, 800, 3000, 600));
        platforms.add(new Platform(800, 750, 50, 20));
        platforms.add(new Platform(400, 780, 200, 20));
        platforms.add(new Platform(700, 700, 50, 20));
        platforms.add(new Platform(900, 650, 50, 20));
        platforms.add(new Platform(1000, 580, 100, 20));

        // SHould be the ingame time if it works
        Timer timer = new Timer(20, this);
        timer.start();

        //makes the game controller and the keys
        GameController controller = new GameController(player);
        addKeyListener(controller);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Painst platforms alongside player
        super.paintComponent(g);
        player.draw(g);

        //lowkey could maybe do smth about the platforms being in an array
        g.setColor(Color.green);
        for (Platform platform : platforms) {
            platform.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //should more or less activate every timer tick (every 20 milliseconds)
        player.applyGravity();
        collisionManager.checkCollisions();
        player.updatePosition();

        playerInfoLabel.setText(player.getStatusText(collisionManager.isOnGround()));
        repaint();
    }
}

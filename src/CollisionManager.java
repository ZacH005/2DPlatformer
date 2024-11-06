import java.util.List;

public class CollisionManager {
    private Player player;
    private List<Platform> platforms;

    public CollisionManager(Player player, List<Platform> platforms) {
        this.player = player;
        this.platforms = platforms;
    }

    public boolean isOnGround() {
        for (Platform platform : platforms) {
            if (player.getX() + player.getWidth() > platform.x &&
                    player.getX() < platform.x + platform.width &&
                    player.getY() + player.getHeight() >= platform.y &&
                    player.getY() + player.getHeight() <= platform.y + platform.height) {
                player.setY(platform.y - player.getHeight());  // Snap to platform
                return true;
            }
        }
        return false;
    }

    public void checkCollisions() {
        if (isOnGround()) {
            player.setVelocityY(0);
            player.setJumping(false);
        } else {
            player.setJumping(true);
        }
    }
}

import java.util.List;

public class CollisionManager {
    private Player player;
    private List<Platform> platforms;

    public CollisionManager(Player player, List<Platform> platforms) {
        this.player = player;
        this.platforms = platforms;
    }

    // Checks if the player is on the ground (landing on top of a platform)
    public boolean isOnGround() {
        for (Platform platform : platforms) {
            if (player.getX() + player.getWidth() > platform.x &&
                    player.getX() < platform.x + platform.width &&
                    player.getY() + player.getHeight() <= platform.y &&
                    player.getY() + player.getHeight() + player.getVelocityY() >= platform.y) {  // Predict the next position

                // Position player on top of the platform and stop vertical movement
                player.setY(platform.y - player.getHeight());
                player.setVelocityY(0);
                return true;
            }
        }
        return false;
    }

    // Main collision check method
    public void checkCollisions() {
        player.setJumping(!isOnGround());
        checkSideCollisions();
    }

    // Method to handle side collisions (left and right) with prediction
    private void checkSideCollisions() {
        int nextX = player.getX() + player.getVelocityX();  // Predicted next horizontal position
        int nextY = player.getY() + player.getVelocityY();  // Predicted next vertical position

        for (Platform platform : platforms) {
            // Moving right into a platform's left side
            if (player.getVelocityX() > 0 && // Moving right
                    nextX + player.getWidth() > platform.x &&
                    player.getX() < platform.x &&
                    player.getY() + player.getHeight() > platform.y &&
                    player.getY() < platform.y + platform.height) {

                // Position player just to the left of the platform and stop horizontal movement
                player.setX(platform.x - player.getWidth());
                player.setVelocityX(0);
            }
            // Moving left into a platform's right side
            else if (player.getVelocityX() < 0 && // Moving left
                    nextX < platform.x + platform.width &&
                    player.getX() + player.getWidth() > platform.x + platform.width &&
                    player.getY() + player.getHeight() > platform.y &&
                    player.getY() < platform.y + platform.height) {

                // Position player just to the right of the platform and stop horizontal movement
                player.setX(platform.x + platform.width);
                player.setVelocityX(0);
            }
            // Moving downwards and hitting the bottom side of a platform
            if (player.getVelocityY() > 0 && // Moving down
                    nextY + player.getHeight() > platform.y &&
                    player.getY() < platform.y &&
                    player.getX() + player.getWidth() > platform.x &&
                    player.getX() < platform.x + platform.width) {

                // Position player on top of the platform and stop vertical movement
                player.setY(platform.y - player.getHeight());
                player.setVelocityY(0);
            }
            // Moving upwards and hitting the underside of a platform
            else if (player.getVelocityY() < 0 && // Moving up
                    nextY < platform.y + platform.height &&
                    player.getY() > platform.y &&
                    player.getX() + player.getWidth() > platform.x &&
                    player.getX() < platform.x + platform.width) {

                // Position player below the platform and stop upward movement
                player.setY(platform.y + platform.height);
                player.setVelocityY(0);
            }
        }
    }
}

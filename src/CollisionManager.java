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

                // this should get it to stay ontop of platform
                player.setY(platform.y - player.getHeight());
                if (!player.isJumping())    {
                    player.setVelocityY(0);
                }
                return true;
            }
        }
        return false;
    }

    public void checkCollisions() {
        player.setJumping(!isOnGround()); // just makes sure that jumping happens only when not on ground, also allows for yvelocity to reset because now it recognizes the difference between being on ground and jumping

        //  TODO: need to make it detect side-on collision
        for (Platform platform : platforms) {
            //based on the side its walking into set velocity to 0 depending on the movement key pressed.
            // Check if player is colliding with the left side of the platform
            if (player.getX() + player.getWidth() > platform.x && player.getX() < platform.x &&
                    player.getY() + player.getHeight() > platform.y && player.getY() < platform.y + platform.height) {
                player.setX(platform.x - player.getWidth()); // position the player on the left side of the platform

            } else if (player.getX() < platform.x + platform.width && player.getX() + player.getWidth() > platform.x + platform.width &&
                    player.getY() + player.getHeight() > platform.y && player.getY() < platform.y + platform.height) {
                player.setX(platform.x + platform.width); // position the player on the right side of the platform
                // Check if player is colliding with the right side of the platform
            } else if (player.getY() < platform.y + platform.height && player.getY() + player.getHeight() > platform.y + platform.height &&
                    player.getX() + player.getWidth() > platform.x && player.getX() < platform.x + platform.width) {
                //player.setVelocityY(-(player.getVelocityY()/2));
                player.setY(platform.y + platform.height ); // position the player below the platform
                // Check if player is colliding with the bottom of the platform
            }
        }

    }
}

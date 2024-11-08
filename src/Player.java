import java.awt.*;

public class Player {
    private int x, y, width, height;
    private int velocityX = 0, velocityY = 0;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -15;
    private final int MOVE_SPEED = 10;
    private boolean isJumping = false;
    private final int maxVelocityY = 18;


    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void applyGravity() {
        if (isJumping && velocityY <= maxVelocityY) {
            velocityY += GRAVITY;
        }
    }

    public void moveLeft() {
        velocityX = -MOVE_SPEED;
    }
    public void moveRight() {
        velocityX = MOVE_SPEED;
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = JUMP_STRENGTH;
        }
    }

    public void stopHorizontalMovement() {
        velocityX = 0;
    }

    public void updatePosition() {
        x += velocityX;
        y += velocityY;
    }

    public void draw(Graphics g) {
        //makes color and actually makes the player
        g.setColor(new Color(128, 0, 123));
        g.fillRect(x, y, width, height);
    }

    public String getStatusText(boolean onGround) {
        return "Position: (" + x + ", " + y + ") | X Velocity: " + velocityX + " | Y Velocity: " + velocityY + " | Jumping: " + isJumping + " | On Ground: " + onGround;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getGRAVITY() {
        return GRAVITY;
    }

    public int getJUMP_STRENGTH() {
        return JUMP_STRENGTH;
    }

    public int getMOVE_SPEED() {
        return MOVE_SPEED;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
}

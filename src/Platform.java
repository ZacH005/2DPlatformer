import java.awt.*;

public class Platform {
    public int x, y, width, height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }
}

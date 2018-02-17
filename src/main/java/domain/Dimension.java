package domain;

public class Dimension {
    public final int WIDTH;
    public final int HEIGHT;

    public Dimension(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    @Override
    public String toString() {
        return WIDTH + "X" + HEIGHT;
    }
}

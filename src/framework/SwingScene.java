package framework;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SwingScene extends JPanel {

    private final ArrayList<SpriteRenderer> spriteRenderers;

    public SwingScene() {
        // we are using a game loop to repaint, so we don't want swing randomly doing it for us
        this.setIgnoreRepaint(true);
        this.spriteRenderers = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Not very efficient to draw the layers but suffices for the use case at hand.
        // We allow 4 layers which should suffice.
        for (int i: new int[] {0, 1, 2, 3}) {
            spriteRenderers.forEach((sprite) -> {
                if (sprite.getOrderOfDrawing() == i) {
                    sprite.Render(g2d);
                }
            });
        }
    }

    public void Add(SpriteRenderer spriteRenderer) {
        this.spriteRenderers.add(spriteRenderer);
    }

    public void Remove(SpriteRenderer spriteRenderer) {
        this.spriteRenderers.remove(spriteRenderer);
    }

    public void Update() {
        repaint();
    }
}

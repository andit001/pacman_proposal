package framework;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class SwingScene extends JPanel {

    private final ArrayList<SpriteRenderer> spriteRenderers;

    private final ArrayList<UILabel> uiLabels;

    public SwingScene() {
        // we are using a game loop to repaint, so we don't want swing randomly doing it for us
        this.setIgnoreRepaint(true);
        this.spriteRenderers = new ArrayList<>();
        this.uiLabels = new ArrayList<>();

        // Some sample code to add a button.
//        JButton button = new JButton(
//                new AbstractAction() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        System.out.println("Clicked it!");
//                    }
//                }
//        );
//        add(button);
//        button.setText("Click me?");
    }

    public Font loadFont(String fontFile, float fontSize) {
        Font font;
        InputStream is = getClass().getResourceAsStream(fontFile);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return font;
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

        uiLabels.forEach(
                uiLabel -> uiLabel.Render(g2d)
        );
    }

    protected JLabel CreateLabel(Font font, String text, float fontSize, Color color) {
        JLabel label = new JLabel();
        label.setText(text);
        Font sizedFont = font.deriveFont(fontSize);
        label.setFont(sizedFont);
        label.setForeground(color);

        return label;
    }

    public void Add(SpriteRenderer spriteRenderer) {
        this.spriteRenderers.add(spriteRenderer);
    }

    public void Remove(SpriteRenderer spriteRenderer) {
        this.spriteRenderers.remove(spriteRenderer);
    }

    public void Add(UILabel uiLabel) {
        this.uiLabels.add(uiLabel);
    }

    public void Remove(UILabel uiLabel) {
        this.uiLabels.remove(uiLabel);
    }

    public void Update() {
        repaint();
    }
}

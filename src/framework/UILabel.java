package framework;

import java.awt.*;

public class UILabel extends ComponentBehaviour {
    private String fontFile;
    private Font font;
    private String text;

    private Color color = Color.white;

    private float fontSize = 12f;

    // Some values have changed. Reload the font in the next update period.
    private boolean isDirty = false;

    @Override
    public void Start() {
        Update();

        Main.swingScene.Add(this);
    }

    @Override
    public void Update() {
        if (isDirty) {
            isDirty = false;
            font = Main.swingScene.loadFont(fontFile, fontSize);
        }
    }

    public void Render(Graphics2D graphics2D) {
        graphics2D.setFont(font);
        graphics2D.setColor(color);

        graphics2D.drawString(text, (float)transform.position.x, (float)transform.position.y);
    }

    @Override
    public void Destroy() {
        Main.swingScene.Remove(this);
    }

    public void setColor(Color color) {
        isDirty = true;
        this.color = color;
    }

    public void setFont(String fontFile) {
        isDirty = true;
        this.fontFile = "/" + fontFile;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(float fontSize) {
        isDirty = true;
        this.fontSize = fontSize;
    }
}

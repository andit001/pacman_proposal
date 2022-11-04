package game;

import framework.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelBehaviour extends ComponentBehaviour {
    private SpriteRenderer levelSprite;

    private int levelWidthInPixel;
    private int levelHeightInPixel;

    public Vector2 playerStartTile;
    private String levelToLoad;

    public LevelBehaviour(String levelToLoad) {
        this.levelToLoad = levelToLoad;
    }

    @Override
    public void Awake() {
        levelSprite = (SpriteRenderer) GetComponent(SpriteRenderer.class.getSimpleName());
        playerStartTile = new Vector2();

        loadLevel();

        // Set the size of the window to the size of the level.
        Main.setFrameSize(levelWidthInPixel, levelHeightInPixel);
    }

    private void loadLevel() {
        assert(levelToLoad != null);

        File file = new File(levelToLoad);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int row = 0;
        while (scanner.hasNextLine()) {
            int column = 0;
            String line = scanner.nextLine();

            for (char character: line.toCharArray()) {
                int type = 0;
                if (character == '#') {
                    type = 1;
                }
                if (character == ' ') {
                    type = 0;
                }
                if (character == 'S') {
                    playerStartTile.x = column * Game.tileWidth;
                    playerStartTile.y = row * Game.tileHeight;
                    type = 0;
                }
                createTileObject(type, column, row);
                ++column;
            }

            ++row;
        }
    }

    private void createTileObject(int type, int column, int row) {
        GameObject newTile = new GameObject();
        SpriteRenderer levelSprite = new SpriteRenderer();
        levelSprite.SetTexture("Assets/leveltileset.png");
        levelSprite.setTiling(2);
        levelSprite.setNextTile(type);
        newTile.AddComponent(levelSprite);

        newTile.transform.position.x = column *  levelSprite.getTileWidth();
        newTile.transform.position.y = row * levelSprite.getTileHeight();

        levelWidthInPixel = Math.max(
                (int)newTile.transform.position.x + levelSprite.getTileWidth(),
                levelWidthInPixel
        );

        levelHeightInPixel = Math.max(
                (int)newTile.transform.position.y + levelSprite.getTileHeight(),
                levelHeightInPixel
        );

        Main.getScene().AddGameObject(newTile);
    }
}

package game;

import framework.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelBehaviour extends ComponentBehaviour {

    enum TileType {
        Floor(0),
        Wall(1);

        private final int value;
        TileType(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    private SpriteRenderer levelSprite;

    private int levelWidthInPixel;
    private int levelHeightInPixel;

    public Vector2 playerStartTile;
    private String levelToLoad;

    private char level[][];

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

    public TileType getTileTypeAtPosition(double x, double y) {
        assert (x >= 0 && x < levelWidthInPixel);
        assert (y >= 0 && y < levelHeightInPixel);

        int column = (int)Math.floor(x / Game.tileWidth);
        int row = (int)Math.floor(y / Game.tileHeight);

        if (row >= level[0].length) {
            return null;
        }
        if (column >= level.length) {
            return null;
        }

        return characterToType(level[column][row]);
    }

    public TileType getTileTypeAtPosition(Vector2 position) {
//        int x = (int)Math.round(position.x);
//        int y = (int)Math.round(position.y);
        return getTileTypeAtPosition(position.x, position.y);
    }

    public Rect getTileRectAtPosition(Vector2 position) {
        int column = (int)Math.floor((int)position.x / Game.tileWidth);
        int row = (int)Math.floor((int)position.y / Game.tileHeight);

        return new Rect(
                column * Game.tileWidth,
                row * Game.tileHeight,
                Game.tileWidth,
                Game.tileWidth
        );
    }

    private void loadLevel() {
        assert(levelToLoad != null);

        var lines = new ArrayList<String>();

        Scanner scanner = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" +levelToLoad);
            scanner = new Scanner(inputStream);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }

        int columns = lines.get(0).length();
        int rows = lines.size();

        level = new char[columns][rows];

        int row = 0;
        for (var line: lines) {
            int column = 0;

            for (var character: line.toCharArray()) {
                level[column][row] = character;

                createTileObject(character, column, row);
                ++column;
            }
            ++row;
        }
    }

    private TileType characterToType(char character) {
        TileType tileType = TileType.Floor;
        if (character == '#') {
            tileType = TileType.Wall;
        }
        if (character == ' ') {
            tileType = TileType.Floor;
        }
        if (character == 'S') {
            tileType = TileType.Floor;
        }

        return tileType;
    }

    private void createTileObject(char character, int column, int row) {
        int type = characterToType(character).getValue();

        if (character == 'S') {
            playerStartTile.x = column * Game.tileWidth;
            playerStartTile.y = row * Game.tileHeight;
        }

        GameObject newTile = new GameObject();
        SpriteRenderer levelSprite = new SpriteRenderer();
        levelSprite.SetTexture("Assets/leveltileset.png");
        levelSprite.setTiling(2);
        levelSprite.setCurrentTile(type);
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

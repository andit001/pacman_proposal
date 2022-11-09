package framework;

public class Rect {
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public Rect(double x, double y, double width, double height) {
        xMin = x;
        xMax = x + width;
        yMin = y;
        yMax = y + height;
    }

    public Vector2 getPosition() {
        return new Vector2(xMin, yMin);
    }

    public Vector2 getSize() {
        return new Vector2(getWidth(), getHeight());
    }

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public double getYMin() {
        return yMin;
    }

    public double getYMax() {
        return yMax;
    }

    public double getWidth() {
        return xMax - xMin;
    }

    public double getHeight() {
        return yMax - yMin;
    }

    public Vector2 getCenter() {
        return new Vector2(
                xMin + getWidth() / 2,
                yMin + getHeight() / 2
        );
    }

    public boolean overlaps(Rect other) {
        // Zero area can't overlap.
        if (getWidth() == 0.0 || getHeight() == 0.0 || other.getWidth() == 0.0 || other.getHeight() == 0.0) {
            return false;
        }

        if (getXMin() > other.getXMax() || getXMax() < other.getXMin()) {
            return false;
        }

        if (getYMin() > other.getYMax() || getYMax() < other.getYMin()) {
            return false;
        }

        return true;
    }
}

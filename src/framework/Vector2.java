package framework;

public class Vector2 {
    public double x;
    public double y;

    public Vector2() {}

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector) {
        x = vector.x;
        y = vector.y;
    }

    /**
     * @return A new normalized vector with the same direction.
     */
    public Vector2 normalized() {
        if (x == 0 && y == 0) {
            return new Vector2();
        }

        double squared = x*x + y*y;
        double root = Math.sqrt(Math.abs(squared));
        Vector2 newVector = new Vector2(this);

        newVector.x /= root;
        newVector.y /= root;

        return newVector;
    }

    public Vector2 add(Vector2 other) {
        Vector2 newVector = new Vector2(this);

        newVector.x += other.x;
        newVector.y += other.y;

        return newVector;
    }

    public Vector2 subtract(Vector2 other) {
        Vector2 newVector = new Vector2(this);

        newVector.x -= other.x;
        newVector.y -= other.y;

        return newVector;
    }

    public Vector2 multiply(double scalar) {
        Vector2 newVector = new Vector2(this);

        newVector.x *= scalar;
        newVector.y *= scalar;

        return newVector;
    }
}

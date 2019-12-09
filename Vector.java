import java.util.Objects;

public class Vector {

    private double x;
    private double y;
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector plus(Vector b) {
        return new Vector(this.x + b.getX(), this.y + b.getY());
    }

    public Vector minus(Vector b) {
        return new Vector(this.x - b.getX(), this.y - b.getY());
    }

    public double dot(Vector b) {
        return (this.x*b.getX() + this.y*b.getY());
    }

    public Vector times(double b) {
        return new Vector(this.x*b, this.y*b);
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }


    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 &&
                Double.compare(vector.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public double distanceTo(Vector projection) {
        return Math.sqrt(Math.pow(this.getX()-projection.getX(),2)+Math.pow(this.getY()-projection.getY(),2));
    }
}
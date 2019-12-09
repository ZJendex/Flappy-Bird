public class Bird {
    private Vector position;
    private Vector velocity;
    private double radius;
    private double g;

    /** Constructor for the bird
     * @param vector
     * @param vector1
     * @param r*/
    public Bird(Vector vector, Vector vector1, double r, double g) {
        this.position = vector;
        this.velocity = vector1;
        this.radius = r;
        this.g = g;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public double getRadius() {
        return radius;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
    /**
     * changes the speed of the bird based off of an inputted acceleration
     */
    public void accelerate(Vector v) {
        double a;
        double b;
        a = velocity.getX() + v.getX();
        b = velocity.getY() + v.getY()-g;
        Vector m = new Vector(a, b);
        setVelocity(m);
    }

    public void setAccelerateBack(){
        double a;
        double b;
        a = velocity.getX();
        b = velocity.getY()-g;
        Vector m = new Vector(a, b);
        setVelocity(m);
    }


    public void setG(double g) {
        this.g = g;
    }

    public double getG() {
        return g;
    }

    /**
     * changes the position of the bird based off of its velocity
     */
    public void drift() {
        double a;
        double b;
        a = getPosition().getX() + getVelocity().getX();
        b = getPosition().getY() + getVelocity().getY();
        Vector m = new Vector(a, b);
        setPosition(m);
    }



    /**
     * Adapted from https://stackoverflow.com/a/34951168/1435803.
     */
    public boolean intersects(LineSegment line) {
        Vector a = line.getA();
        Vector b = line.getB();
        Vector v = b.minus(a);
        Vector w = position.minus(a);
        double c1 = w.dot(v);
        double c2 = v.dot(v);
        Vector projection; // Projection of center onto line
        if (c1 <= 0) { // Projection is beyond point a
            projection = a;
        } else if (c2 <= c1) { // Projection is beyond point b
            projection = b;
        } else { // Projection is within line segment
            projection = a.plus(v.times(c1 / c2));
        }
        return position.distanceTo(projection) <= radius;
    }

    public void setBird(Vector vector, Vector vector1, double v, int i) {
        this.position=vector;
        this.velocity=vector1;
        this.radius=v;
        this.g=i;
    }
}

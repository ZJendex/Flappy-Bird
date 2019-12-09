public class Background {
    private Vector position;
    private Vector velocity;
    public Background(Vector position, Vector velocity){
        this.position=position;
        this.velocity=velocity;

    }

    public LineSegment[][] createBackground(int length, double[] randomArray){
        LineSegment[][] result = new LineSegment[length][7];
        int j = 0;
        for (double i = position.getX(); i <length+position.getX(); i=i+6) {
            result[j]=Pipes.createPipes(new Vector(i,randomArray[j]));
            j++;
        }
        return result;
    }



    public Vector getPosition() {
        return position;
    }
    public Vector getVelocity() {
        return velocity;
    }
    public void setPosition(Vector position) {
        this.position = position;
    }
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
    public void drift() {
        double a;
        double b;
        a = getPosition().getX() + getVelocity().getX();
        b = getPosition().getY() + getVelocity().getY();
        Vector m = new Vector(a, b);
        setPosition(m);
    }

    public void setBackground(Vector vector, Vector vector1) {
        this.position=vector;
        this.velocity=vector1;
    }
}

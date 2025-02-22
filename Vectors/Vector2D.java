package Vectors;

public class Vector2D implements IVector {
    private double x;
    private double y;

    public Vector2D(){}

    public Vector2D(double[] components){
        setComponents(components);
    }

    @Override
    public void setComponents(double[] components){
        this.x = components[0];
        this.y = components[1];
    }

    public double[] getComponents() {
        double[] result = new double[2];
        result[0] = x; result[1] = y;
        return result;
    }

    public double cdot(IVector vector) {
        double[] components = vector.getComponents();
        return x*components[0] + y*components[1];
    }

    public double abs(){
        return Math.sqrt(x*x + y*y);
    }

    @Override
    public String toString(){
        return ("(" + this.getComponents()[0] + ", " + this.getComponents()[1] + ")");
    }
}

package Vectors;

public class Polar2DInheritance extends Vector2D{

    public Polar2DInheritance(double[] components) {
        super(components);
    }

    public double getAngle(){
        double[] components = this.getComponents();
        return Math.atan(components[1]/components[0]);
    }
}

package Vectors;

public class Polar2DAdapter implements IPolar2D, IVector{
    private Vector2D srcVector;

    Polar2DAdapter(Vector2D vector2D){
        this.srcVector = vector2D;
    }

    @Override
    public void setComponents(double[] components){
        this.srcVector.setComponents(components);
    }

    @Override
    public double abs(){
        return this.srcVector.abs();
    }

    @Override
    public double getAngle(){
        double[] components = getComponents();
        return Math.atan(components[1]/components[0]);
    }

    @Override
    public double cdot(IVector vector){
        return this.srcVector.cdot(vector);
    }

    @Override
    public double[] getComponents() {
        return this.srcVector.getComponents();
    }
}

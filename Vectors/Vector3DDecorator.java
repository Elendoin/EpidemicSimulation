package Vectors;
import java.util.Arrays;

public class Vector3DDecorator implements IVector {
    private IVector srcVector;
    private double z = 0;

    public Vector3DDecorator(IVector iVector){
        this.srcVector = iVector;
        double[] components = new double[3];
        double[] vectComp = iVector.getComponents();
        components[0] = vectComp[0];
        components[1] = vectComp[1];
        if(vectComp.length == 3){
            components[2] = iVector.getComponents()[2];
        }
        this.setComponents(components);
    }

    public Vector3DDecorator(IVector iVector, double z){
        this.srcVector = iVector;
        double[] components = new double[3];
        components[0] = iVector.getComponents()[0];
        components[1] = iVector.getComponents()[1];
        components[2] = z;
        this.setComponents(components);
    }

    @Override
    public void setComponents(double[] components) {
        this.srcVector.setComponents(components);
        if(components.length == 3) {
            z = components[2];
        }
    }

    @Override
    public double abs(){
        return Math.sqrt(Math.pow(srcVector.getComponents()[0], 2) + Math.pow(srcVector.getComponents()[1], 2) + Math.pow(z, 2));
    }

    @Override
    public double cdot(IVector vector){
        double[] comp1 = getComponents();
        double[] comp2 = vector.getComponents();
        if(comp2.length < 3)
            return comp1[0] * comp2[0] + comp1[1] * comp2[1];
        return comp1[0] * comp2[0] + comp1[1] * comp2[1] + comp1[2] * comp2[2];
    }

    @Override
    public double[] getComponents() {
        double[] components = new double[3];
        components[0] = srcVector.getComponents()[0];
        components[1] = srcVector.getComponents()[1];
        components[2] = z;
        return components;
    }

    public Vector3DDecorator cross(IVector vector){
        double[] components = new double[3];
        double[] comp1 = getComponents();
        double[] comp2 = vector.getComponents();
        if(comp2.length < 3)
        {
            comp2 = Arrays.copyOf(comp2, 3);
            comp2[2] = 0;

        }

        components[0] = comp1[1] * comp2[2] - (comp1[2] * comp2[1]);
        components[1] = comp1[2] * comp2[0] - (comp1[0] * comp2[2]);
        components[2] = comp1[0] * comp2[1] - (comp1[1] * comp2[0]);
        Vector2D v2 = new Vector2D(components);
        return new Vector3DDecorator(v2, components[2]);

    }

    public IVector getSrcVector(){
        return srcVector;
    }
}

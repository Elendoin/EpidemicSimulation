package Vectors;
import java.util.Arrays;

public class Vector3DInheritance extends Vector2D {
    private double z = 0;

    public Vector3DInheritance(double[] components){
        setComponents(components);
    }

    @Override
    public void setComponents(double[] components){
        if(components.length == 3) {
            super.setComponents(components);
            z = components[2];
        }
    }

    @Override
    public double abs(){
        double[] comp = getComponents();
        return Math.sqrt(comp[0]*comp[0] + comp[1]*comp[1] + comp[2]*comp[2]);
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
    public double[] getComponents(){
        double[] components = new double[3];
        components[0] = super.getComponents()[0];
        components[1] = super.getComponents()[1];
        components[2] = z;
        return components;
    }

    public Vector3DInheritance cross(IVector vector){
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
        return new Vector3DInheritance(components);
    }
}

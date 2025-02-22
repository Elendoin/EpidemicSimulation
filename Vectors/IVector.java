package Vectors;

public interface IVector {
    public double abs();
    public double cdot(IVector vector);
    public double[] getComponents();
    public void setComponents(double[] components);
}

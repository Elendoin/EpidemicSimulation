package Movement;
import Specimen.Specimen;
import Vectors.IVector;
import Vectors.Vector2D;

public class SpecimenMovement2D implements IMovement{
    private Specimen specimen;

    @Override
    public IVector move(IVector vector){
        double[] oldPos = specimen.getPosition().getComponents();
        double[] components = vector.getComponents();

        if(components.length != 2 && oldPos.length != 2)
        {
            return null;
        }

        double[] newComponents = new double[2];
        newComponents[0] = oldPos[0] + components[0];
        newComponents[1] = oldPos[1] + components[1];

        return new Vector2D(newComponents);
    }

    public void setSpecimen(Specimen specimen){
        this.specimen = specimen;
    }
}

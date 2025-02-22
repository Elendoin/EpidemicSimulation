package States;
import Specimen.*;

public abstract class SpecimenState {
    protected Specimen specimen;

    public SpecimenState(Specimen specimen){
        this.specimen = specimen;
    }

    abstract public void setColor();
    abstract public void setFlag();
    abstract public SpecimenState deepCopy(Specimen specimen);
}

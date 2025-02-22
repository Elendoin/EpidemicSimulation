package Specimen;
import States.*;
import java.awt.*;
import Vectors.*;

public class Specimen {
    private SpecimenState state;
    private boolean activeFlag;
    private Color color;
    private IVector position;
    private int sickVicinity = 0;
    private int sickTime = 0;

    public Specimen(IVector position){
        this.state = new Vulnerable(this);
        this.position = position;
        this.state.setColor();
        this.state.setFlag();
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setActiveFlag(boolean flag){
        activeFlag = flag;
    }

    public void setPosition(IVector newPos){
        this.position = newPos;
    }

    public void setState(SpecimenState state){
        this.state = state;
        this.state.setFlag();
        this.state.setColor();
    }

    public void setSickTime(int sickTime) {
        this.sickTime = sickTime;
    }

    public void setSickVicinity(int sickVicinity){
        this.sickVicinity = sickVicinity;
    }

    public int getSickTime(){
        return this.sickTime;
    }

    public int getSickVicinity(){
        return this.sickVicinity;
    }

    public IVector getPosition(){
        return position;
    }

    public Color getColor() { return this.color; }

    public boolean getActiveFlag(){
        return this.activeFlag;
    }

    public SpecimenState getState(){
        return this.state;
    }

    public Specimen deepCopy2D() {
        double[] newComponents = new double[2];
        newComponents[0] = position.getComponents()[0];
        newComponents[1] = position.getComponents()[1];
        IVector newPosition = new Vector2D(newComponents);
        Specimen copy = new Specimen(newPosition);

        SpecimenState newState = this.state.deepCopy(copy);
        copy.setState(newState);
        copy.setColor(this.color);
        copy.setActiveFlag(this.activeFlag);
        copy.setSickTime(this.sickTime);
        copy.setSickVicinity(this.sickVicinity);

        return copy;
    }

}

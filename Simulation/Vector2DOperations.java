package Simulation;
import Vectors.Vector2D;
import java.util.Random;

public class Vector2DOperations {
    private static final Random random = new Random();

    public static Vector2D randomEdgePoint(int frameX, int frameY){
        int roll = random.nextInt(4);
        double[] components = new double[2];

        switch(roll){
            case 0:
                components[0] = 0;
                components[1] = random.nextInt(frameY);
                return new Vector2D(components);
            case 1:
                components[0] = random.nextInt(frameX);
                components[1] = frameY;
                return new Vector2D(components);
            case 2:
                components[0] = frameX;
                components[1] = random.nextInt(frameY);
                return new Vector2D(components);
            case 3:
                components[0] = random.nextInt(frameX);
                components[1] = 0;
                return new Vector2D(components);
            default:
                return null;
        }
    }

    public static Vector2D randomSpeed(Vector2D vector, double upperBound, double lowerBound){
        double[] components = vector.getComponents();
        double multiplier = random.nextDouble(upperBound-lowerBound)+lowerBound;
        components[0] = (int)(components[0] * multiplier);
        if(Math.abs(components[0]) >= 3){
            components[0] = Math.signum(components[0])* 2;
        }
        components[1] = (int)(components[1] * multiplier);
        if(Math.abs(components[1]) >= 3){
            components[1] = Math.signum(components[1])*2;
        }
        Vector2D newVector = new Vector2D(components);
        if(newVector.abs() == 0){
            return vector;
        }
        return newVector;
    }

    public static Vector2D randomDirectionVector(int max, int min){
        double[] components = new double[2];
        components[0] = random.nextInt(max - min) + min;
        components[1] = random.nextInt(max - min) + min;
        Vector2D newVector = new Vector2D(components);
        if(newVector.abs() == 0){
            components[0] = 1;
            components[1] = 1;
            return new Vector2D(components);
        }
        return new Vector2D(components);
    }

    public static Vector2D normalize(Vector2D vector){
        double[] components = vector.getComponents();
        double abs = vector.abs();
        components[0] = (components[0]/abs);
        components[1] = (components[1]/abs);
        return new Vector2D(components);
    }

    public static Vector2D middleDirection(Vector2D currentPos, int frameX, int frameY){
        double[] components = currentPos.getComponents();
        components[0] = frameX/2 - components[0];
        components[1] = frameY/2 - components[1];
        return randomSpeed(normalize(new Vector2D(components)), 3, 2);
    }

    public static double distance(Vector2D from, Vector2D to){
        double[] components = new double[2];
        components[0] = to.getComponents()[0] - from.getComponents()[0];
        components[1] = to.getComponents()[1] - from.getComponents()[0];
        return Math.sqrt(components[0]*components[0] + components[1]*components[1]);
    }

    public static boolean checkForEdge(Vector2D vector, int frameX, int frameY){
        double[] components = vector.getComponents();
        if(components[0] < 0 || components[0] > frameX || components[1] < 0 || components[1] > frameY){
            return true;
        }
        else{
            return false;
        }
    }

}

package scene_objects;

import util.Vector;

// sphere centered on origin for testing ray marching in general
public class Sphere extends SceneObject {

    double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double getSDF(Vector from) {
        return from.magnitude() - radius;
    }
}

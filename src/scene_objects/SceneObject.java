package scene_objects;

import util.Vector;

public abstract class SceneObject {

    // signed distance function
    public abstract double getSDF(Vector point);
}

package scene_objects;

import util.Vector;

public class Mandelbulb extends SceneObject {

    // some rendering constants
    private final static int ITERATIONS = 300;
    private final static double DEPTH_OF_FIELD = 2;
    private double power;

    public Mandelbulb(double power) {
        this.power = power;
    }

    // distance estimator from
    // http://blog.hvidtfeldts.net/index.php/2011/09/distance-estimated-3d-fractals-v-the-mandelbulb-different-de-approximations/
    @Override
    public double getSDF(Vector point) {
        // initialisation
        Vector z = new Vector(point);
        double dr = 1; // running derivative
        double r = 0; // escape time length
        // iteration
        int i;
        for (i = 0; i < ITERATIONS; i++) {
            r = z.magnitude();
            if (r > DEPTH_OF_FIELD) {
                break;
            }
            // conversion to polar coordinates
            double theta = Math.acos(z.getX3() / r);
            double phi = Math.atan2(z.getX2(), z.getX1());
            dr = Math.pow(r, power - 1.0) * power * dr + 1.0;
            // scaling and rotation
            double zr = Math.pow(r, power);
            theta = theta * power;
            phi = phi * power;
            // conversion back to cartesian coordinates
            z = new Vector(Math.sin(theta) * Math.cos(phi), Math.sin(theta) * Math.sin(phi), Math.cos(theta));
            z = z.scale(zr).add(point);
        }
        // distance estimator
        return 0.5 * Math.log(r) * r / dr;
    }
}

import scene_objects.SceneObject;
import util.Pixel;
import util.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    // scene constants (magic numbers which make the fractal appear and not some strange abstract art or a black screen)
    private final static int WORLD_UNIT_PIXELS = 800; //200
    private final static Vector CAMERA_ORIGIN = new Vector(-1.8, 0, 0);

    // ray marching constants
    private final static int MAX_MARCHING_STEPS = 50;
    private final static double MINIMUM_DISTANCE = 0.0005; //0.01

    // scene size in world units
    int width_px;
    int height_px;

    public Renderer(int width_px, int height_px) {
        this.width_px = width_px;
        this.height_px = height_px;
    }

    public BufferedImage render(SceneObject object) {
        // display grey scale image based on number of steps taken for now
        BufferedImage image = new BufferedImage(width_px, height_px, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width_px; x++) {
            for (int y = 0; y < height_px; y++) {
                int depth = (int) (255 * rayMarch(CAMERA_ORIGIN, convertPixelToWorld(new Pixel(x, y)), object));
                image.setRGB(x, y, new Color(depth, depth, depth).getRGB());
            }
            // Display progress every 10 lines
            if (x % 10 == 0) {
                System.out.println(String.format("%.2f", 100 * x / (float) (width_px - 1)) + "% completed");
            }
        }
        return image;
    }

    private double rayMarch(Vector from, Vector to, SceneObject object) {
        double totalDistance = 0;
        Vector direction = Vector.distanceVector(from, to).normalise();
        int steps;
        for (steps = 0; steps < MAX_MARCHING_STEPS; steps++) {
            Vector scaledDirection = direction.scale(totalDistance);
            Vector P = from.add(scaledDirection);
            double distance = object.getSDF(P);
            totalDistance += distance;
            if (distance < MINIMUM_DISTANCE) {
                break;
            }
        }
        return 1.0 - (double) steps / (double) MAX_MARCHING_STEPS;
    }

    // put camera origin in the middle of image
    private Pixel getCameraOrigin() {
        return new Pixel(width_px / 2, height_px / 2);
    }

    // for finding the ray marching vector
    private Vector convertPixelToWorld(Pixel p) {
        double x2 = (p.x - getCameraOrigin().x) / (double) WORLD_UNIT_PIXELS;
        double x3 = (getCameraOrigin().y - p.y) / (double) WORLD_UNIT_PIXELS;
        return new Vector(0, x2, x3);
    }

}

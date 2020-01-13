import scene_objects.Mandelbulb;
import scene_objects.Sphere;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Demo {

    // image size
    private static final int WIDTH_PX = 5040; //1280
    private static final int HEIGHT_PX = 3840; //960


    public static void main(String[] args) throws IOException {
        createImage("Mandelbulb", "demo.png");
    }

    // choose between different examples
    private static void createImage(String object, String filename) throws IOException {
        Renderer renderer = new Renderer(WIDTH_PX, HEIGHT_PX);
        BufferedImage image = null;

        switch (object) {
            case "Sphere":
                image = renderer.render(new Sphere(10));
                break;
            case "Mandelbulb":
                image = renderer.render(new Mandelbulb(7));
                break;
            default:
                break;
        }

        // Save the image to disk
        if (image != null) {
            File save = new File(filename);
            ImageIO.write(image, "png", save);
        }
    }
}

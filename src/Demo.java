import scene_objects.Mandelbulb;
import scene_objects.Sphere;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Demo {

    private static final int WIDTH_PX = 640;
    private static final int HEIGHT_PX = 480;
    private static final String OUTPUT_FILE = "output.png";


    public static void main(String[] args) throws IOException {
        createImage("Sphere");
    }

    // choose between different examples
    private static void createImage(String object) throws IOException {
        Renderer renderer = new Renderer(WIDTH_PX, HEIGHT_PX);
        BufferedImage image = null;

        switch (object) {
            case "Sphere":
                image = renderer.render(new Sphere(10));
                break;
            case "Mandelbulb":
                image = renderer.render(new Mandelbulb());
                break;
            default:
                break;
        }

        // Save the image to disk
        if (image != null) {
            File save = new File(OUTPUT_FILE);
            ImageIO.write(image, "png", save);
        }
    }
}

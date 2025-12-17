import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageFileManager {
    private static final String FORMAT = "jpg";

    public static OFImage loadImage(File imageFile) {
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            if (bufferedImage == null) return null;
            return new OFImage(bufferedImage);
        } catch (IOException e) {
            return null;
        }
    }

    public static void saveImage(OFImage image, File file) {
        try {
            ImageIO.write(image, FORMAT, file);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan gambar: " + e.getMessage());
        }
    }
}
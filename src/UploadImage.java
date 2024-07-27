import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.*;

/** This class is used for uploading an image.
 * Each image gets as name the code of the rental in order
 * to be able to find it easier*/

public class UploadImage extends StartMenu
{
    JFileChooser fc = new JFileChooser();
    int result = fc.showOpenDialog(null);

public void initialize(String code)
{
    if (result != JFileChooser.APPROVE_OPTION) {
        System.exit(0);
    }


    String path = fc.getSelectedFile().getAbsolutePath();
    File folder = new File("images");
    // Get the destination of the folder and the new image and changes its name to the code of the rental
    String destination = folder.getAbsolutePath() + File.separator +code+ ".jpg";

    try {
        // Copy file from source to destination
        FileChannel source = new FileInputStream(path).getChannel();
        FileChannel dest = new FileOutputStream(destination).getChannel();
        dest.transferFrom(source, 0, source.size());
        source.close();
        dest.close();
    } catch (
            IOException e) {
        e.printStackTrace();
    }
}
}
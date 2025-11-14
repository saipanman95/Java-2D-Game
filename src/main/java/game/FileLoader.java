package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    public BufferedImage loadImage(String classpathPath) {
        // src/main/resources is the classpath root → use a path like "player/xxx.png"
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(classpathPath)) {
            if (in == null) {
                throw new IllegalStateException("Resource not found on classpath: " + classpathPath);
            }
            return ImageIO.read(in);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load image: " + classpathPath, e);
        }
    }

    public List<String> loadMapLines(String classPath) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(classPath);
        if (in == null) {
            throw new IllegalArgumentException("Resource not found on classpath: " + classPath);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            List<String> lines = new ArrayList<>();
            for (String line; (line = br.readLine()) != null; ) lines.add(line);
            return lines;
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load map: " + classPath, e);
        }
    }
}

package it.unibo.mvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File file;

    public Controller() {
        this.file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt");
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public String getPath(File file) {
        return file.getAbsolutePath();
    }

    public void writeOnFile(String line) throws IOException {
        try (final BufferedWriter w = new BufferedWriter(new FileWriter(this.file.getPath()))) {
            w.write(line);
            w.newLine();
        }
    }
}

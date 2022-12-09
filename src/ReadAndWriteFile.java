import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFile {
    static String file = "student.txt";
    public static void writeFile(List<Student> students) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(students);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
        }
    }

    public static List<Student> readFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<Student>) objectInputStream.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

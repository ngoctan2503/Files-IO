import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDemo {

    public static void main(String[] args) throws IOException {
        // Tạo file mới
        createNewFile("my-file.txt");

        // Ghi dữ liệu vào file
        writeFile("my-file.txt", "Đây là nội dung file!");

        // Đọc dữ liệu từ file
        String data = readFile("my-file.txt");
        System.out.println("Nội dung file: " + data);

        // Lấy đường dẫn file
        String filePath = getAbsolutePath("my-file.txt");
        System.out.println("Đường dẫn file: " + filePath);

        // Xóa file
        deleteFile("my-file.txt");

        // Tạo thư mục
        createDirectory("new-directory");
        
        // Lưu file
        saveFile(filePath, null);
    }

    private static void createNewFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.createNewFile()) {
            System.out.println("File " + fileName + " đã được tạo thành công!");
        } else {
            System.out.println("File " + fileName + " đã tồn tại!");
        }
    }

    private static void writeFile(String fileName, String content) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
        System.out.println("Dữ liệu đã được ghi vào file " + fileName + " thành công!");
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = fileReader.read()) != -1) {
            sb.append((char) i);
        }
        fileReader.close();
        return sb.toString();
    }

    private static String getAbsolutePath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    private static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("File " + fileName + " đã được xóa thành công!");
        } else {
            System.out.println("Lỗi khi xóa file " + fileName + "!");
        }
    }

    private static void createDirectory(String directoryName) {
        File directory = new File(directoryName);
        if (directory.mkdir()) {
            System.out.println("Thư mục " + directoryName + " đã được tạo thành công!");
        } else {
            System.out.println("Thư mục " + directoryName + " đã tồn tại!");
        }
    }
 // Lưu file
    public static void saveFile(String filePath, byte[] data) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, data);
        System.out.println("File đã lưu thành công.");
    }

}

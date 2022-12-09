import java.io.File;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        ManageStudent manageStudent = new ManageStudent();
        String menu = "---- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ----\n" +
                "Chọn chức năng theo số (để tiếp tục)\n" +
                "1. Xem danh sách sinh viên\n" +
                "2. Thêm mới\n" +
                "3. Cập nhật\n" +
                "4. Xóa\n" +
                "5. Sắp xếp\n" +
                "6. Đọc từ file\n" +
                "7. Ghi vào file\n" +
                "8. Thoát     \n" +
                "Chọn chức năng:";

        while (true) {
            int choice;
            System.out.println(menu);
            choice = manageStudent.checkLuachon();
            switch (choice) {
                case 1 -> manageStudent.show();
                case 2 -> manageStudent.add();
                case 3 -> manageStudent.update();
                case 4 -> manageStudent.deleteStudent();
                case 5 -> manageStudent.sort();
                case 6 -> manageStudent.ReadFromFile();
                case 7 -> manageStudent.WriteToFile();
                case 8 -> System.exit(0);
                default -> System.out.println("Chọn từ 1 tới 8");
            }
        }
    }
}
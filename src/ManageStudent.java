import java.sql.SQLOutput;
import java.util.*;

public class ManageStudent {
    static Scanner scanner = new Scanner(System.in);
    public List<Student> studentList = new ArrayList<>();

    static String regexString = "^[a-zA-Z]*$";
    static String regexForMs = "^[a-zA-Z0-9]*$";

    public void show() {
        System.out.println("Nhập Enter để hiển thị danh sách sinh viên");
        scanner.nextLine();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }


    public boolean checkMsSV(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void add() {
        Student newStudent = create();
        studentList.add(newStudent);
        System.out.println("Thêm sinh viên thành công");

    }

    public Student create() {

        String id, name, gender, address;

        while (true) {
            System.out.println("Nhập mã số sinh viên");
            id = scanner.nextLine();
            if (id.matches(regexForMs)) {
                if (checkMsSV(id)) {
                    System.out.println("Mã sinh viên trùng, nhập mã khác");
                } else {
                    break;
                }
            } else {
                System.out.println("Nhập lại đúng định dạng");
            }
        }

        while (true) {
            System.out.println("Nhập tên sinh viên ");
            name = scanner.nextLine();
            if (name.matches(regexString)) {
                break;
            } else {
                System.out.println("Nhập lại đúng định dạng");
            }
        }

        int age;
        do {
            try {
                System.out.println("Nhập tuổi");
                age = Integer.parseInt(scanner.nextLine());
                if (age < 10 || age > 60) {
                    System.out.println("Nhập tuổi từ 18 tới 60");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Sai định dạng! Vui lòng chọn lại");
            }
        } while (true);

        do {
            System.out.println("Nhập giới tính");
            gender = scanner.nextLine();
            if (gender.matches(regexString)) {
                break;
            } else {
                System.out.println("Nhập lại đúng định dạng");
            }
        } while (true);

        while (true) {
            System.out.println("Nhập địa chỉ");
            address = scanner.nextLine();
            if (address.matches(regexString)) {
                break;
            } else {
                System.out.println("Nhập lại đúng định dạng");
            }
        }
        double average;
        do {
            System.out.println("Nhập điểm trung bình");
            try {
                average = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Sai định dạng! Vui lòng chọn lại");
            }
        } while (true);


        Student student = new Student(id, name, age, gender, address, average);
        return student;
    }

    public void update() {

        int index = findIndexByID();
        if (index != -1) {
            System.out.println("Nhập mã số sinh viên mới");
            String id = scanner.nextLine();
            studentList.get(index).setId(id);
            System.out.println("Nhập tên sinh viên mới");
            String name = scanner.nextLine();
            studentList.get(index).setName(name);
            System.out.println("Nhập tuổi mới");
            int age = Integer.parseInt(scanner.nextLine());
            studentList.get(index).setAge(age);
            System.out.println("Nhập giới tính mới");
            String gender = scanner.nextLine();
            studentList.get(index).setGender(gender);
            System.out.println("Nhập địa chỉ mới");
            String address = scanner.nextLine();
            studentList.get(index).setAddress(address);
            System.out.println("Nhập điểm trung bình mới");
            double average;
            while (true) {
                try {
                    average = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (Exception e) {
                    System.err.println("Sai định dạng! Vui lòng chọn lại");
                }
            }
            studentList.get(index).setAverage(average);
            System.out.println("Cập nhật sinh viên thành công");
        } else {
            System.out.println("ID này không tồn tại");
            System.out.println("Nhấn Enterd để thoát");
            scanner.nextLine();
        }
    }

    public int findIndexByID() {
        System.out.println("Nhập ID sinh viên cần tìm");
        String id = scanner.nextLine();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteStudent() {
        int index = findIndexByID();
        if (index != -1) {
            System.out.println("Nhập y để đồng ý, nhập ký tự bất kỳ để thoát");
            String choice = scanner.nextLine();
            if (choice.equals("y")) {
                studentList.remove(index);
            } else {
                System.out.println("Hủy bỏ xóa");
                ;
            }
        } else {
            System.out.println("Không tìm được sinh viên với mã sinh viên trên");
            System.out.println("Nhập Enter để thoát");
            scanner.nextLine();
        }
    }

    public void sort() {
        int choose = 3;
        while (true) {
            System.out.println("----Sắp xếp sinh viên theo điểm trung bình---");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Sắp xếp điểm trung bình tăng dần");
            System.out.println("2. Sắp xếp điểm trung bình giảm dần---");
            System.out.println("3. Thoát");
            System.out.println("Chọn chức năng: ");
            do {
                if (choose > 3) System.out.println("Vui lòng nhập lại");
                choose = Integer.parseInt(scanner.nextLine());
            } while (choose > 3);
            switch (choose) {
                case 1 -> sortAvgUp();
                case 2 -> sortAvgDown();
                case 3 -> {
                    return;
                }
            }
        }
    }


    public void sortAvgUp() {
        Student temp;
        for (int i = 0; i < studentList.size() - 1; i++) {
            for (int j = i + 1; j < studentList.size(); j++) {
                if (studentList.get(i).getAverage() > studentList.get(j).getAverage()) {
                    temp = studentList.get(i);
                    studentList.set(i, studentList.get(j));
                    studentList.set(j, temp);
                }
            }
        }
        show();
    }

    public void sortAvgDown() {
        Student temp;
        for (int i = 0; i < studentList.size() - 1; i++) {
            for (int j = i + 1; j < studentList.size(); j++) {
                if (studentList.get(i).getAverage() < studentList.get(j).getAverage()) {
                    temp = studentList.get(i);
                    studentList.set(i, studentList.get(j));
                    studentList.set(j, temp);
                }
            }
        }
        show();
    }

    public int checkLuachon() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("Sai định dạng! Vui lòng chọn lại");
            }
        }
        return choice;
    }

    public void ReadFromFile() {
        System.out.println("Đọc từ File sẽ mất dữ liệu hiện tại, bạn có muốn tiếp tục? (nhập y để đồng ý hoặc other to exit) ");
        String choice = scanner.nextLine();
        if (choice.equals("y")) {
            studentList = ReadAndWriteFile.readFile();
            System.out.println("Đọc file thành công, chọn chức năng xem danh sách để kiểm tra.");
        }
    }

    public void WriteToFile() {
        System.out.println("Ghi vào File sẽ mất dữ liệu đang lưu, bạn có muốn tiếp tục? (nhập y để đồng ý hoặc other to exit) ");
        String choice = scanner.nextLine();
        if (choice.equals("y")) {
            ReadAndWriteFile.writeFile(studentList);
            System.out.println("Ghi file thành công, chạy lại chương trình và chọn chức năng đọc file để kiểm tra.");
        }
    }
}

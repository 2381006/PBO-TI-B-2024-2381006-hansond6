import java.util.Scanner;

public class Main {
    public static String[] todos = new String[10];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Tambahkan beberapa data awal
        addTodoList("Belajar");
        addTodoList("Menggambar");
        addTodoList("Menulis");
        addTodoList("Menyetir");

        // Tampilkan menu utama
        showMainMenu();
    }

    // Menampilkan daftar TODO
    public static void showTodoList() {
        System.out.println("TODO LIST:");
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] != null) {
                System.out.println((i + 1) + ". " + todos[i]);
            }
        }
    }

    // Menambah TODO ke dalam daftar
    public static void addTodoList(String todo) {
        resizeIfFull();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    // Mengecek dan mengubah ukuran array jika penuh
    private static void resizeIfFull() {
        boolean isFull = true;
        for (String todo : todos) {
            if (todo == null) {
                isFull = false;
                break;
            }
        }
        if (isFull) {
            String[] temp = todos;
            todos = new String[todos.length * 2];
            System.arraycopy(temp, 0, todos, 0, temp.length);
        }
    }

    // Menghapus TODO berdasarkan nomor
    public static boolean removeTodoList(Integer number) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        for (int i = number - 1; i < todos.length - 1; i++) {
            todos[i] = todos[i + 1];
        }
        todos[todos.length - 1] = null;
        return true;
    }

    // Memeriksa apakah nomor TODO valid
    private static boolean isSelectedTodoNotValid(Integer number) {
        return number <= 0 || number > todos.length || todos[number - 1] == null;
    }

    // Mengedit TODO
    public static boolean editTodoList(int number, String newTodo) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }

    // Menampilkan menu utama
    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTodoList();
            System.out.println("Menu:");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("Pilih");

            switch (selectedMenu) {
                case "1":
                    showMenuAddTodoList();
                    break;
                case "2":
                    showMenuRemoveTodoList();
                    break;
                case "3":
                    showMenuEditTodoList();
                    break;
                case "4":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu dengan benar!");
            }
        }
    }

    // Menu untuk menambah TODO
    public static void showMenuAddTodoList() {
        System.out.println("MENAMBAH TODO LIST");
        String todo = input("Todo (x jika batal)");
        if (!todo.equals("x")) {
            addTodoList(todo);
        }
    }

    // Menu untuk menghapus TODO
    public static void showMenuRemoveTodoList() {
        System.out.println("MENGHAPUS TODO LIST");
        String number = input("Nomor yang dihapus (x jika batal)");
        if (!number.equals("x")) {
            boolean success = removeTodoList(Integer.parseInt(number));
            if (!success) {
                System.out.println("Gagal menghapus TODO: " + number);
            }
        }
    }

    // Menu untuk mengedit TODO
    public static void showMenuEditTodoList() {
        System.out.println("MENGEDIT TODO LIST");
        String selectedTodo = input("Nomor yang ingin diedit (x jika batal)");
        if (!selectedTodo.equals("x")) {
            String newTodo = input("Masukkan TODO baru (x jika batal)");
            if (!newTodo.equals("x")) {
                boolean success = editTodoList(Integer.parseInt(selectedTodo), newTodo);
                if (success) {
                    System.out.println("Berhasil mengedit TODO.");
                } else {
                    System.out.println("Gagal mengedit TODO.");
                }
            }
        }
    }

    // Fungsi input
    public static String input(String info) {
        System.out.print(info + ": ");
        return scanner.nextLine();
    }
}

package cf.baradist;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    database.read();
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    database.write();
                }
            }).start();
        }
    }
}

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        JsonParser parser = new JsonParser();
        Group group = parser.parse();

        System.out.println(group.toString());

        BuildJson.newJson();

        Delete();
    }

    private static void Delete() {
        // удаление файла
        File file = new File("newJson.json");
        System.out.print("\nУдалить файл? Y/N: ");
        Scanner scanner = new Scanner(System.in);;
        String choice = scanner.nextLine();
        if (Objects.equals(choice, "Y") || Objects.equals(choice, "y")){
            if (file.delete()) {
                System.out.println(file.getName() + " deleted");
            } else {
                System.out.println(file.getName() + " not deleted");
            }
        }
    }
}

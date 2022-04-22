import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BuildJson {

    public static void newJson() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Название группы ");
        String nameGroup = scanner.nextLine();
        System.out.print("Количество студентов ");
        int count = Integer.parseInt(scanner.next());

        JSONObject newGroup = new JSONObject();

        newGroup.put("group", nameGroup);

        JSONArray newStudents = new JSONArray();
        for (int i = 0;i < count; i++)
        {
            JSONObject student = new JSONObject();
            System.out.print("Фамилия ");
            String sName = scanner.next();
            student.put("sName",sName);

            System.out.print("Оценка ");
            int mark = Integer.parseInt(scanner.next());
            student.put("mark", mark);

            newStudents.add(student);
        }


        newGroup.put("students", newStudents);

        try{
            FileWriter fileWriter = new FileWriter("newJson.json");
            fileWriter.write(newGroup.toString());
            fileWriter.flush();
            fileWriter.close();
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

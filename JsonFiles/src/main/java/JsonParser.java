import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public Group parse(){
        Group group = new Group();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("group.json")){

            JSONObject root = (JSONObject) parser.parse(reader);
            String nameGroup = (String) root.get("group");

            JSONArray studentsJSONArray = (JSONArray) root.get("students");

            List<Student> studentList = new ArrayList<>();
            for (Object item: studentsJSONArray){
                JSONObject studentsJSONObject = (JSONObject) item;

                String sName = (String) studentsJSONObject.get("sName");
                long mark = (long) studentsJSONObject.get("mark");

                Student student = new Student(sName, (int) mark);
                studentList.add(student);
            }

            group.setNameGruop(nameGroup);
            group.setStudentList(studentList);

            return group;

        } catch (Exception e) {
            System.out.println("Error "+e.toString());
        }

        return null;
    }

}

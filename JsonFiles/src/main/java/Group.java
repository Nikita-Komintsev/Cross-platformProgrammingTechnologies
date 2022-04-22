import java.util.List;

public class Group {
    private String nameGruop;
    private List<Student> studentList;

    public void setNameGruop(String nameGruop) {
        this.nameGruop = nameGruop;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "nameGruop='" + nameGruop + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}

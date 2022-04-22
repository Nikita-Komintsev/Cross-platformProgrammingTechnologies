public class Student {

    private final String sName;
    private final int mark;

    public Student(String sName, int mark) {
        this.sName = sName;
        this.mark = mark;
    }


    @Override
    public String toString() {
        return "Student{" +
                "sName='" + sName + '\'' +
                ", mark=" + mark +
                '}';
    }
}

package students;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherNotebook {

     private static final int FAILING_LIMIT = 2;

     private List<Student> students = new ArrayList<>();

     public void readFromFile(Path path){
          try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
               String line;
               while ((line = bufferedReader.readLine())!= null){
                  processLine(line);
               }
          }catch (IOException ioe){
               throw new IllegalStateException("Cannot read file", ioe);
          }
     }

     private void processLine(String line){
          String[] lineParts = line.split(";");
          Student student = new Student(lineParts[0], lineParts[1]);
          addGradesToStudent(student,lineParts);
          students.add(student);
     }

     private void addGradesToStudent(Student student, String[] lineParts){
          for (int i = 2;i < lineParts.length; i++){
               student.addGrade(convertStringToInt(lineParts[i]));
          }
     }

     private int convertStringToInt(String numberString){
          return Integer.parseInt(numberString);
     }

     public List<Student> getStudents() {
          return students;
     }

     public List<String> findFailingStudents() {
         List<String> names= new ArrayList<>();
         for (Student actual : students){
              checkIsValidAndAddToList(names,actual);
         }
         return names;
     }

     private void checkIsValidAndAddToList(List<String> names, Student student){
          if(checkIsAverageOverLimit(student)){
               names.add(student.getName());
          }
     }

     private boolean checkIsAverageOverLimit(Student student){
          return getAverageGrade(student.getGrades()) < FAILING_LIMIT;
     }

     private double getAverageGrade(List<Integer> grades){
          return grades.stream().collect(Collectors.averagingDouble(Integer::doubleValue));
     }
}

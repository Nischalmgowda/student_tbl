package Table.Student_table.Service;


import Table.Student_table.Model.Student;
import Table.Student_table.Response.StudentResponse;

import java.util.Map;
import java.util.Optional;

public interface StudentService {
    public Student save(Student student) throws Exception;
    StudentResponse saveStudent(Student student)throws Exception;

    StudentResponse moveToTrash(Map<String, String> formData)throws Exception;

    Optional<Student> findById(String id)throws Exception;

    StudentResponse get(Map<String, String> formData)throws Exception;

    StudentResponse getDeleted()throws Exception;

    StudentResponse getAll()throws Exception;

    StudentResponse getPaginated(Map<String, String> formData)throws Exception;

    StudentResponse searchPaginated(Map<String, String> formData)throws Exception;
}

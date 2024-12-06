package Table.Student_table.Service;

import Table.Student_table.DTO.StudentDTO;
import Table.Student_table.Model.Student;
import Table.Student_table.Response.StudentResponse;
import Table.Student_table.Specification.StudentSpecification;
import Table.Student_table.Repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.String.valueOf;

@Service
public class StudentServiceImplementation implements StudentService {
@Autowired
    StudentRepository studentRepository;
    private static final Logger logger=  LoggerFactory.getLogger(StudentServiceImplementation.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Student save(Student student) throws Exception {

        return studentRepository.save(student);
    }

    @Override
    public StudentResponse saveStudent(Student student) throws Exception {
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            if(checkDuplicate(student)){
                studentResponse.setStudentDTO(new StudentDTO(student));
                studentResponse.setSuccess(false);
                studentResponse.setError("Duplicate pico branch!");
            }
            else{
                if(student.getId()==null){
                    student.setCreated_by("User");
                    student.setCreation_time(new Date());
                }else{
                    student.setLast_modified_by("User");
                    student.setLast_modification_time(new Date());
                }
                studentResponse.setStudentDTO(
                        new StudentDTO(
                                this.save(student)
                        ));
                studentResponse.setSuccess(true);
                studentResponse.setError("");
            }
            logger.trace("Completed Successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        return studentResponse;
    }

    @Override
    public StudentResponse moveToTrash(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<Student> optionalStudent=this.findById(valueOf(formData.get("id")));
            if(optionalStudent.isPresent()){
                Student student=optionalStudent.get();
                student.setIsDeleted(1);
                student.setDeleted_by("User");
                student.setDeletion_time(new Date());
                studentResponse.setSuccess(true);
                studentResponse.setError("");
                studentResponse.setStudentDTO(new StudentDTO(this.save(student)));
            }
            else{
                studentResponse.setSuccess(false);
                studentResponse.setError("Error occurred while moving branch details to trash!! Please Check it!");
            }

        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;

    }

    @Override
    public Optional<Student> findById(String id) throws Exception {
        return studentRepository.findById(id);
    }

    @Override
    public StudentResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<Student> optionalStudent=this.findById(valueOf(formData.get("id")));
            if(optionalStudent.isPresent()){
                Student student=optionalStudent.get();
                studentResponse.setSuccess(true);
                studentResponse.setError("");
                studentResponse.setStudentDTO(new StudentDTO(this.save(student)));
            }
            else {
                studentResponse.setSuccess(false);
                studentResponse.setError("Error occurred while fetching branch details!! Please try again!");
            }
            logger.trace("Completed Successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }

    @Override
    public StudentResponse getDeleted() throws Exception {
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse.setData(
                    getStudentDTOS(studentRepository.findAllByIsDeleted(1)));
            studentResponse.setSuccess(true);
            studentResponse.setError("");
            logger.trace("Completed successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }

    @Override
    public StudentResponse getAll() throws Exception {
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            Sort sort=Sort.by(Sort.Direction.ASC,"name");
            studentResponse.setData(getStudentDTOS(studentRepository.findAllByIsDeleted(0,sort)));
            studentResponse.setSuccess(true);
            studentResponse.setError("");
            logger.trace("Completed successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }

    private List<StudentDTO> getStudentDTOS(List<Student> students) {
        List<StudentDTO> studentDTOS=new ArrayList<>();
        for(Student student:students){
            studentDTOS.add(new StudentDTO(student));
        }
        return studentDTOS;
    }
    private boolean checkDuplicate(Student student) {
        List<Student> students;
        if(student.getId()==null){
            students=studentRepository.findAllByIsDeletedAndName(0,student.getName());
        }
        else{
            students=studentRepository.findAllByIsDeletedAndNameAndIdIsNot(0,student.getName(),student.getId());
        }
        return !students.isEmpty();
    }
    @Override
    public StudentResponse getPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null? 0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
            String sortFiled=formData.get("sort_field")==null? "name":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
            Sort sort;
            if (sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC, sortFiled);
            }
            Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
            Page<Student> page=studentRepository.findAllByIsDeleted(0,  pageable);
            studentResponse.setRecordsTotal(page.getTotalElements());
            studentResponse.setRecordsFiltered(page.getTotalElements());
            studentResponse.setTotalPages(page.getTotalPages());
            studentResponse.setData(getStudentDTOS(page.getContent()));
            studentResponse.setCurrentRecords(studentResponse.getData().size());
            studentResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        }

        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());

        }
        logger.trace("Exiting");
        return studentResponse;

    }

    @Override
    public StudentResponse searchPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null ? 0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null? 10:Integer.parseInt(formData.get("page_size"));
            String searchText=formData.get("search_text")==null? null: String.valueOf(formData.get("search_text"));
            String sortFiled=formData.get("sort_field")==null? "name":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null?"asc":formData.get("sort_order");
            Sort sort;
            if(sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC,sortFiled);
            }
            Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
            Page<Student> page;
            if(searchText==null){
                page=studentRepository.findAllByIsDeleted(0,pageable);
            }
            else{
                page=studentRepository.findAll(StudentSpecification.textInAllColumns(searchText),pageable);
            }
            studentResponse.setRecordsTotal(page.getTotalElements());
            studentResponse.setRecordsFiltered(page.getTotalElements());
            studentResponse.setTotalPages(page.getTotalPages());
            studentResponse.setData(getStudentDTOS(page.getContent()));
            studentResponse.setCurrentRecords(studentResponse.getData().size());
            studentResponse.setSuccess(true);
            logger.trace("Completed successfully");

        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }


}

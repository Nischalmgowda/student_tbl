package Table.Student_table.Controller;

import Table.Student_table.Model.Student;
import Table.Student_table.Response.StudentResponse;
import Table.Student_table.Service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="student_table")
public class StudentController {
    @Autowired
    StudentService studentService;
    private static final Logger logger= LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value="/save",method= RequestMethod.POST)
    public StudentResponse save(@Valid @RequestBody Student student){
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse=studentService
                    .saveStudent(student);
        }catch(Exception ex){
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }
    @RequestMapping(value="/trash", method= RequestMethod.POST)
    public StudentResponse moveToTrash(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse=studentService.moveToTrash(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }
    @RequestMapping(value="/get", method=RequestMethod.POST)
    public StudentResponse get(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse=studentService.get(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }
    @RequestMapping(value="/get_deleted", method=RequestMethod.POST)
    @PostMapping("/get-deleted")
    public StudentResponse getDeleted(){
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse=studentService.getDeleted();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }
    @RequestMapping(value="/get_all", method=RequestMethod.POST)
    public StudentResponse getAll(){
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse=studentService.getAll();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }
    @RequestMapping(value="/get_student_branch", method=RequestMethod.POST)
    public StudentResponse getPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse=studentService.getPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }
    @RequestMapping(value="/search_student_branch", method=RequestMethod.POST)
    public StudentResponse searchPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        StudentResponse studentResponse=new StudentResponse();
        try{
            studentResponse=studentService.searchPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            studentResponse.setSuccess(false);
            studentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return studentResponse;
    }

}

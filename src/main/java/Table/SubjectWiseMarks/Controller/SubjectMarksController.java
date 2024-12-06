package Table.SubjectWiseMarks.Controller;

import Table.SubjectWiseMarks.Model.SubjectMarks;
import Table.SubjectWiseMarks.Response.SubjectMarksResponse;
import Table.SubjectWiseMarks.Service.SubjectMarksService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "student_marks")
public class SubjectMarksController {
    @Autowired
    SubjectMarksService subjectMarksService;
    private static final Logger logger= LoggerFactory.getLogger(SubjectMarksController.class);

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public SubjectMarksResponse save(@Valid @RequestBody SubjectMarks subjectMarks){
        logger.trace("Entering:");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.saveStudentDetails(subjectMarks);
        }catch (Exception ex){
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }
    @RequestMapping(value="/trash", method=RequestMethod.POST)
    public SubjectMarksResponse moveToTrash(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.moveToTrash(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }
    @RequestMapping(value="/get", method=RequestMethod.POST)
    public SubjectMarksResponse get(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.get(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }
    @RequestMapping(value="/get_all", method=RequestMethod.POST)
    public SubjectMarksResponse getAll(){
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.getAll();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }

    @RequestMapping(value="/get_deleted", method=RequestMethod.POST)
    public SubjectMarksResponse getDeleted(){
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.getDeleted();
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }
    @RequestMapping(value="/get_student_marks_details", method=RequestMethod.POST)
    public SubjectMarksResponse getPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.getPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }
    @RequestMapping(value="/search_student_marks_details", method=RequestMethod.POST)
    public SubjectMarksResponse searchPaginated(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.searchPaginated(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }
    @RequestMapping(value="/search_student_marks_details_repository", method=RequestMethod.POST)
    public SubjectMarksResponse searchPaginatedThroughRepository(@RequestBody Map<String,String> formData){
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            subjectMarksResponse=subjectMarksService.searchPaginatedThroughRepository(formData);
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }
}

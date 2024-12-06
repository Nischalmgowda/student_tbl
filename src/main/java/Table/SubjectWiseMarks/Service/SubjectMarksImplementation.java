package Table.SubjectWiseMarks.Service;

import Table.SubjectWiseMarks.DTO.SubjectMarksDTO;
import Table.SubjectWiseMarks.Model.SubjectMarks;
import Table.SubjectWiseMarks.Response.SubjectMarksResponse;
import Table.SubjectWiseMarks.Repository.SubjectMarksRepository;
import Table.SubjectWiseMarks.Specification.SubjectMarksSpecification;
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
public class SubjectMarksImplementation implements SubjectMarksService {
    @Autowired
    SubjectMarksRepository subjectMarksRepository;

    private static final Logger logger= LoggerFactory.getLogger(SubjectMarksImplementation.class);
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public SubjectMarksResponse saveStudentDetails(SubjectMarks subjectMarks) throws Exception {
        logger.trace("Entering saveStudentDetails method");
        SubjectMarksResponse subjectMarksResponse = new SubjectMarksResponse();

        try {
            double totalMarks = subjectMarks.getMarks_1() + subjectMarks.getMarks_2() + subjectMarks.getMarks_3();
            subjectMarks.setTotalMarks(totalMarks);
            if (subjectMarks.getId() == null) {
                subjectMarks.setCreated_by("User");
                subjectMarks.setCreation_time(new Date());
                subjectMarks.setPercentage(calculatePercentage(totalMarks));
            } else {
                totalMarks = subjectMarks.getMarks_1() + subjectMarks.getMarks_2() + subjectMarks.getMarks_3();
                subjectMarks.setTotalMarks(totalMarks);
                subjectMarks.setLast_Modified_by("User");
                subjectMarks.setLast_Modification_time(new Date());
                subjectMarks.setPercentage(calculatePercentage(totalMarks));
            }
            subjectMarksResponse.setSubjectMarksDTO(new SubjectMarksDTO(this.save(subjectMarks)));
            subjectMarksResponse.setSuccess(true);
            subjectMarksResponse.setError("");

            logger.trace("Completed saveStudentDetails successfully");
        } catch (Exception ex) {
            logger.error("Error in saveStudentDetails: " + ex.getMessage(), ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }

        return subjectMarksResponse;
    }
    private double calculatePercentage(double totalMarks) {
        double totalSubjects = 3;
        return (totalMarks / (totalSubjects * 100)) * 100;
    }

    @Override
    public SubjectMarksResponse moveToTrash(Map<String, String> formData) throws Exception {
        logger.info("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<SubjectMarks> optionalSubjectMarks=this.findById(valueOf(formData.get("id")));
            if(optionalSubjectMarks.isPresent()){
                SubjectMarks subjectMarks=optionalSubjectMarks.get();
                subjectMarks.setIsDeleted(1);
                subjectMarks.setDeleted_by("User");
                subjectMarks.setDeletion_time(new Date());
                subjectMarksResponse.setSuccess(true);
                subjectMarksResponse.setError("");
                subjectMarksResponse.setSubjectMarksDTO(new SubjectMarksDTO(this.save(subjectMarks)));
            }
            else{
                subjectMarksResponse.setSuccess(false);
                subjectMarksResponse.setError("Error occurred while moving branch details to trash!! Please Check it!");
            }
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }

    @Override
    public Optional<SubjectMarks> findById(String id) throws Exception {
        return subjectMarksRepository.findById(id);
    }

    @Override
    public SubjectMarks save(SubjectMarks subjectMarks) throws Exception {
        return subjectMarksRepository.save(subjectMarks);
    }

    @Override
    public SubjectMarksResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            Optional<SubjectMarks> optionalSubjectMarks = this.findById(valueOf(formData.get("id")));

            if(optionalSubjectMarks.isPresent()){
                SubjectMarks subjectMarks = optionalSubjectMarks.get();
                subjectMarksResponse.setSuccess(true);
                subjectMarksResponse.setError("");
                subjectMarksResponse.setSubjectMarksDTO(new SubjectMarksDTO(this.save(subjectMarks)));
            }
            else {
                subjectMarksResponse.setSuccess(false);
                subjectMarksResponse.setError("Error occurred while fetching branch details!! Please try again!");
            }
            logger.trace("Completed Successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }

    @Override
    public SubjectMarksResponse getAll() throws Exception {
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();

        try{
            Sort sort=Sort.by(Sort.Direction.ASC,"studentId");
            subjectMarksResponse.setData(getPicoBranchDetailsDTOS(subjectMarksRepository.findByIsDeleted(0,sort)));
            subjectMarksResponse.setSuccess(true);
            subjectMarksResponse.setError("");
            logger.trace("Completed successfully");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }

    @Override
    public SubjectMarksResponse getDeleted() throws Exception {
            logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
            try{
                //  subjectMarksResponse.setData(getPicoBranchDetailsDTOS(picoBranchDetailsRepository.findAllByIsDeleted(1)));
                subjectMarksResponse.setData(
                        getPicoBranchDetailsDTOS(subjectMarksRepository.findAllByIsDeleted(1)));
                subjectMarksResponse.setSuccess(true);
                subjectMarksResponse.setError("");
                logger.trace("Completed successfully");
            }catch(Exception ex){
                logger.error(ex.getMessage(),ex);
                subjectMarksResponse.setSuccess(false);
                subjectMarksResponse.setError(ex.getMessage());
            }
            logger.trace("Exiting");
            return subjectMarksResponse;

    }

    @Override
    public SubjectMarksResponse getPaginated(Map<String, String> formData) {
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null? 0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
            String sortFiled=formData.get("sort_field")==null? "id":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
            Sort sort;
            if (sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC, sortFiled);
            }
            Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
            Page<SubjectMarks> page=subjectMarksRepository.findAllByIsDeleted(0,  pageable);
            subjectMarksResponse.setRecordsTotal(page.getTotalElements());
            subjectMarksResponse.setRecordsFiltered(page.getTotalElements());
            subjectMarksResponse.setTotalPages(page.getTotalPages());
            subjectMarksResponse.setData(getPicoBranchDetailsDTOS(page.getContent()));
            subjectMarksResponse.setCurrentRecords(subjectMarksResponse.getData().size());
            subjectMarksResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        }

        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());

        }
        logger.trace("Exiting");
        return subjectMarksResponse;

    }

    @Override
    public SubjectMarksResponse searchPaginated(Map<String, String> formData) throws Exception {
            logger.trace("Entering");
        SubjectMarksResponse  subjectMarksResponse=new SubjectMarksResponse();
            try{
                logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
                int pageNumber=formData.get("current_page")==null?0:Integer.parseInt(formData.get("current_page"));
                int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
                String searchText=formData.get("search_text")==null?null: String.valueOf(formData.get("search_text"));
                String sortFiled=formData.get("sort_field")==null? "id":formData.get("sort_field");
                String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
                Sort sort;
                if (sortOrder.equals("asc")){
                    sort=Sort.by(Sort.Direction.ASC, sortFiled);
                }
                else{
                    sort=Sort.by(Sort.Direction.DESC, sortFiled);
                }
                Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
                Page<SubjectMarks> page;
                if(searchText==null){
                    page=subjectMarksRepository.findAllByIsDeleted(0,pageable);
                }
                else{
                    page=subjectMarksRepository.findAll(SubjectMarksSpecification.textInAllColumns(searchText),pageable);
                }
                subjectMarksResponse.setRecordsTotal(page.getTotalElements());
                subjectMarksResponse.setRecordsFiltered(page.getTotalElements());
                subjectMarksResponse.setTotalPages(page.getTotalPages());
                subjectMarksResponse.setData(getPicoBranchDetailsDTOS(page.getContent()));
                subjectMarksResponse.setCurrentRecords(subjectMarksResponse.getData().size());
                subjectMarksResponse.setSuccess(true);
                logger.trace("Completed successfully");
            }
            catch(Exception ex){
                logger.error(ex.getMessage(),ex);
                subjectMarksResponse.setSuccess(false);
                subjectMarksResponse.setError(ex.getMessage());

            }
            logger.trace("Exiting");
            return subjectMarksResponse;
        }

    @Override
    public SubjectMarksResponse searchPaginatedThroughRepository(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        SubjectMarksResponse subjectMarksResponse=new SubjectMarksResponse();
        try{
            logger.trace("Data:{}",objectMapper.writeValueAsString(formData));
            int pageNumber=formData.get("current_page")==null?0:Integer.parseInt(formData.get("current_page"));
            int pageSize=formData.get("page_size")==null?10:Integer.parseInt(formData.get("page_size"));
            String searchText=formData.get("search_text")==null?null: String.valueOf(formData.get("search_text"));
            String sortFiled=formData.get("sort_field")==null? "studentId":formData.get("sort_field");
            String sortOrder=formData.get("sort_order")==null? "asc":formData.get("sort_order");
            Sort sort;
            if (sortOrder.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC, sortFiled);
            }
            else{
                sort=Sort.by(Sort.Direction.DESC, sortFiled);
            }
            Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
            if(!searchText.contains("%")){
                searchText= "%"+searchText+"%";

            }
            Page<SubjectMarksDTO> page= subjectMarksRepository.searchPaginated(searchText,pageable);
            subjectMarksResponse.setRecordsTotal(page.getTotalElements());
            subjectMarksResponse.setRecordsFiltered(page.getTotalElements());
            subjectMarksResponse.setTotalPages(page.getTotalPages());
            subjectMarksResponse.setData(page.getContent());
            subjectMarksResponse.setCurrentRecords(subjectMarksResponse.getData().size());
            subjectMarksResponse.setSuccess(true);
            logger.trace("Completed successfully");
        }
        catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            subjectMarksResponse.setSuccess(false);
            subjectMarksResponse.setError(ex.getMessage());

        }
        logger.trace("Exiting");
        return subjectMarksResponse;
    }


    private List<SubjectMarksDTO> getPicoBranchDetailsDTOS(List<SubjectMarks> subjectMarks) {
        List<SubjectMarksDTO> subjectMarksDTOS=new ArrayList<>();
        for(SubjectMarks subjectDetails:subjectMarks){
            subjectMarksDTOS.add(new SubjectMarksDTO(subjectDetails));
        }
        return subjectMarksDTOS;
    }

    private boolean checkDuplicate(SubjectMarks subjectMarks) {
        List<SubjectMarks> subjectMarks1;
        if(subjectMarks.getId()==null){
            subjectMarks1=subjectMarksRepository.findAllByIsDeletedAndStudentId(0,subjectMarks.getStudentId());
        }
        else{
            subjectMarks1=subjectMarksRepository.findAllByIsDeletedAndStudentIdAndIdIsNot(0,subjectMarks.getStudentId(),subjectMarks.getId());
        }
        return !subjectMarks1.isEmpty();
    }
}

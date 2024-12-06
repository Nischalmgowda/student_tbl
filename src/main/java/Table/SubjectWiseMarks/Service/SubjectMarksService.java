package Table.SubjectWiseMarks.Service;

import Table.SubjectWiseMarks.Model.SubjectMarks;
import Table.SubjectWiseMarks.Response.SubjectMarksResponse;

import java.util.Map;
import java.util.Optional;

public interface SubjectMarksService {

    SubjectMarksResponse saveStudentDetails(SubjectMarks subjectMarks)throws Exception;

    SubjectMarksResponse moveToTrash(Map<String, String> formData)throws Exception;

    Optional<SubjectMarks> findById(String id)throws Exception;

    SubjectMarks save(SubjectMarks subjectMarks)throws  Exception;

    SubjectMarksResponse get(Map<String, String> formData)throws Exception;

    SubjectMarksResponse getAll() throws Exception;

    SubjectMarksResponse getDeleted()throws Exception;

    SubjectMarksResponse getPaginated(Map<String, String> formData);

    SubjectMarksResponse searchPaginated(Map<String, String> formData)throws Exception;

    SubjectMarksResponse searchPaginatedThroughRepository(Map<String, String> formData)throws Exception;
}

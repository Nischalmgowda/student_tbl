package Table.SubjectWiseMarks.Repository;

import Table.SubjectWiseMarks.DTO.SubjectMarksDTO;
import Table.SubjectWiseMarks.Model.SubjectMarks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectMarksRepository extends JpaRepository<SubjectMarks, String> {
    List<SubjectMarks> findAllByIsDeletedAndStudentId(int i, String studentId);

    List<SubjectMarks> findAllByIsDeletedAndStudentIdAndIdIsNot(int i, String studentId, String id);
    List<SubjectMarks> findByIsDeleted(int i, Sort sort);

    List<SubjectMarks> findAllByIsDeleted(int i);

    Page<SubjectMarks> findAllByIsDeleted(int isDeleted, Pageable page);

    Page<SubjectMarks> findAll(Specification<SubjectMarks> textInAllColumns, Pageable pageable);

    @Query("SELECT new Table.SubjectWiseMarks.DTO.SubjectMarksDTO (" +
            "subjectMarks.id, " +
            "subjectMarks.studentId, " +
            "subjectMarks.subject_1, " +
            "subjectMarks.subject_2, " +
            "subjectMarks.subject_3, " +
            "subjectMarks.marks_1, " +
            "subjectMarks.marks_2, " +
            "subjectMarks.marks_3, " +
            "subjectMarks.totalMarks, " +
            "subjectMarks.percentage, " +
            "subjectMarks.created_by, " +
            "subjectMarks.creation_time, " +
            "subjectMarks.last_Modified_by, " +
            "subjectMarks.last_Modification_time, " +
            "subjectMarks.isDeleted, " +
            "subjectMarks.deleted_by, " +
            "subjectMarks.deletion_time, " +
            "student.name," +
            "student.classes"+
            ")" +
            "FROM SubjectMarks as subjectMarks " +
            "LEFT JOIN Student student ON subjectMarks.studentId = student.id " +
            "WHERE (lower(student.name) LIKE lower(:searchText) OR lower(subjectMarks.studentId) LIKE lower(:searchText) OR lower(student.classes) LIKE lower(:searchText)) " +
            "AND subjectMarks.isDeleted = 0")
    Page<SubjectMarksDTO> searchPaginated(@Param("searchText") String searchText, Pageable pageable);
}


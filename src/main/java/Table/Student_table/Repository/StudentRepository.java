package Table.Student_table.Repository;

import Table.Student_table.Model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    List<Student> findAllByIsDeletedAndName(int is_deleted, String name);

    List<Student> findAllByIsDeletedAndNameAndIdIsNot(int i, String name, String id);

    List<Student> findAllByIsDeleted(int isDeleted);
    List<Student> findAllByIsDeleted(int isDeleted, Sort sort);

    Page<Student> findAllByIsDeleted(int isDeleted, Pageable pageable);

    Page<Student> findAll(Specification<Student> textInAllColumns, Pageable pageable);
}

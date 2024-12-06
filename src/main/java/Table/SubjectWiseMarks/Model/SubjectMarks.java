package Table.SubjectWiseMarks.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


@Data
@Entity
@Table(name = "subject_marks")
public class SubjectMarks {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Length(max=40)
    @Column(unique=true)
    private String id;
    @Length(max=40)
    private String studentId;
    @Length(max=40)
    private String subject_1;
    @Length(max=40)
    private String subject_2;
    @Length(max=40)
    private String subject_3;

    private double marks_1;

    private double marks_2;

    private double marks_3;

    private double totalMarks;

    private double percentage;
    @Length(max=40)
    private String created_by;

    private Date creation_time;

    @Length(max=40)
    private String last_Modified_by;

    private Date last_Modification_time;

    private int isDeleted;

    @Length(max=40)
    private String deleted_by;

    private Date deletion_time;

}

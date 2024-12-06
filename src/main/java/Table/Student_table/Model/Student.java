package Table.Student_table.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Entity
public class Student {
    @Id
    @Length(max=40)
    @Column(unique=true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Length(max=40)
    private String name;

    @Length(max=40)
    private String classes;

    @Length(max=40)
    private String created_by;

    private Date creation_time;

    @Length(max=40)
    private String last_modified_by;

    private Date last_modification_time;

    @Column(name="is_deleted")
    private int isDeleted;

    @Length(max=40)
    private String deleted_by;

    private Date deletion_time;
}

package Table.Student_table.DTO;


import Table.Student_table.Model.Student;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDTO {
    private String id;

    private String name;

    private String classes;

    private String created_by;

    private Date creation_time;

    private String last_modified_by;

    private Date last_modification_time;

    private int isDeleted;

    private String deleted_by;

    private Date deletion_time;

    public StudentDTO(){

    }
    public StudentDTO(Student student){
        this.id=student.getId();
        this.name=student.getName();
        this.classes =student.getClasses();
        this.created_by=student.getCreated_by();
        this.creation_time=student.getCreation_time();
        this.last_modified_by=student.getLast_modified_by();
        this.last_modification_time=student.getLast_modification_time();
        this.isDeleted=student.getIsDeleted();
        this.deleted_by=student.getDeleted_by();
        this.deletion_time=student.getDeletion_time();
    }
    public StudentDTO(String id,String name,String classes,String created_by,Date creation_time,String last_modified_by,Date last_modification_time,int isDeleted,String deleted_by,Date deletion_time){
        this.id=id;
        this.name=name;
        this.classes =classes;
        this.created_by=created_by;
        this.creation_time=creation_time;
        this.last_modified_by=last_modified_by;
        this.last_modification_time=last_modification_time;
        this.isDeleted=isDeleted;
        this.deleted_by=deleted_by;
        this.deletion_time=deletion_time;
    }
}

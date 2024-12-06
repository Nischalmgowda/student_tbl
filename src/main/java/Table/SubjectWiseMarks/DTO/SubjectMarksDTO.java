package Table.SubjectWiseMarks.DTO;

import Table.SubjectWiseMarks.Model.SubjectMarks;
import lombok.Data;

import java.util.Date;

@Data
public class SubjectMarksDTO {
    private String id;
    private String studentId;
    private String subject_1;
    private String subject_2;
    private String subject_3;
    private double marks_1;
    private double marks_2;
    private double marks_3;
    private double totalMarks;
    private double percentage;
    private String created_by;
    private Date creation_time;
    private String last_Modified_by;
    private Date last_Modification_time;
    private int isDeleted;
    private String deleted_by;
    private Date deletion_time;
    private String name;
    private String classes;

    public SubjectMarksDTO(){

    }

    public SubjectMarksDTO(SubjectMarks subjectMarks){
        this.id=subjectMarks.getId();
        this.studentId =subjectMarks.getStudentId();
        this.subject_1=subjectMarks.getSubject_1();
        this.subject_2=subjectMarks.getSubject_2();
        this.subject_3=subjectMarks.getSubject_3();
        this.marks_1=subjectMarks.getMarks_1();
        this.marks_2=subjectMarks.getMarks_2();
        this.marks_3=subjectMarks.getMarks_3();
        this.totalMarks=subjectMarks.getTotalMarks();
        this.percentage=subjectMarks.getPercentage();
        this.created_by=subjectMarks.getCreated_by();
        this.creation_time=subjectMarks.getCreation_time();
        this.last_Modified_by=subjectMarks.getLast_Modified_by();
        this.last_Modification_time=subjectMarks.getLast_Modification_time();
        this.isDeleted=subjectMarks.getIsDeleted();
        this.deleted_by=subjectMarks.getDeleted_by();
        this.deletion_time=subjectMarks.getDeletion_time();
    }
    public SubjectMarksDTO(String id, String studentId, String subject_1, String subject_2, String subject_3, double marks_1, double marks_2, double marks_3,double totalMarks,double percentage, String created_by, Date creation_time, String last_Modified_by, Date last_Modification_time, int isDeleted, String deleted_by, Date deletion_time, String name,String classes){
        this.id=id;
        this.studentId = studentId;
        this.subject_1=subject_1;
        this.subject_2=subject_2;
        this.subject_3=subject_3;
        this.marks_1=marks_1;
        this.marks_2=marks_2;
        this.marks_3=marks_3;
        this.totalMarks=totalMarks;
        this.percentage=percentage;
        this.created_by=created_by;
        this.creation_time=creation_time;
        this.last_Modified_by=last_Modified_by;
        this.last_Modification_time=last_Modification_time;
        this.isDeleted=isDeleted;
        this.deleted_by=deleted_by;
        this.deletion_time=deletion_time;
        this.name=name;
        this.classes=classes;
    }
}

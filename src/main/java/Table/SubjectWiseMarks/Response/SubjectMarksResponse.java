package Table.SubjectWiseMarks.Response;


import Table.SubjectWiseMarks.DTO.SubjectMarksDTO;
import lombok.Data;

import java.util.List;

@Data
public class SubjectMarksResponse {
    private SubjectMarksDTO subjectMarksDTO;

    private List<SubjectMarksDTO> data;

    private long totalPages;

    private long recordsTotal;

    private long currentRecords;

    private long recordsFiltered;

    private boolean success;

    private String error;
}

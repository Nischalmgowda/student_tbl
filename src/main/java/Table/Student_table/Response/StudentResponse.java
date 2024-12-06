package Table.Student_table.Response;

import Table.Student_table.DTO.StudentDTO;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {
    private StudentDTO studentDTO;

    private List<StudentDTO> data;

    private long totalPages;

    private long recordsTotal;

    private long currentRecords;

    private long recordsFiltered;

    private boolean success;

    private String error;
}

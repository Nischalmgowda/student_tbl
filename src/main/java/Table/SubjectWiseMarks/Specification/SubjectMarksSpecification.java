package Table.SubjectWiseMarks.Specification;

import Table.SubjectWiseMarks.Model.SubjectMarks;
import org.springframework.data.jpa.domain.Specification;

public class SubjectMarksSpecification {
    public static Specification<SubjectMarks> textInAllColumns(String searchText) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText + "%"; 
        }
        String finalText = searchText;
        return (root, query, builder) -> builder.and(
                builder.or(
                        builder.like(builder.lower(root.get("studentId")), finalText),
                        builder.like(builder.lower(root.join("student").get("name")), finalText) // Join to access student name
                ),
                builder.equal(root.get("isDeleted"), 0)
        );
    }
}
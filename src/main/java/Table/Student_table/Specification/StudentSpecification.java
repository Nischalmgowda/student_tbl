package Table.Student_table.Specification;

import Table.Student_table.Model.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> textInAllColumns(String searchText) {
        if (!searchText.contains("%")) {
            searchText = "%" + searchText.toLowerCase() + "%";
        }

        String finalText = searchText;
        return (root, query, builder) -> builder.and(
                builder.or(
                        builder.like(builder.lower(root.get("name")), finalText)
                ),
                builder.equal(root.get("isDeleted"), 0)
        );
    }
}

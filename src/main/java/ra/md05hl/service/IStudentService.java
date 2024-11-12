package ra.md05hl.service;

import ra.md05hl.model.dto.response.StudentCreateDto;
import ra.md05hl.model.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void add(StudentCreateDto request);


}

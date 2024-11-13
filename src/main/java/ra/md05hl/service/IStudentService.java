package ra.md05hl.service;

import ra.md05hl.model.dto.response.StudentCreateDto;
import ra.md05hl.model.dto.response.StudentEditDto;
import ra.md05hl.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();
    void add(StudentCreateDto request);
    void delete(Integer id);
    void update(StudentEditDto request, Integer id);
    Optional<Student> findById(Integer id);
}

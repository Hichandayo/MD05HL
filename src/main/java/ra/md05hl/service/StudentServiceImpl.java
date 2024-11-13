package ra.md05hl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.md05hl.model.dto.response.StudentCreateDto;
import ra.md05hl.model.dto.response.StudentEditDto;
import ra.md05hl.model.entity.Student;
import ra.md05hl.repository.IStudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void add(StudentCreateDto request) {
        String url = uploadService.uploadFileToServer(request.getFile());
        Student entity = Student.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .sex(request.getSex())
                .dateOfBirth(request.getDateOfBirth())
                .imageUrl(url)
                .build();
        studentRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void update(StudentEditDto request, Integer id) {
        Optional<Student> studentId = studentRepository.findById(id);
        if (studentId.isPresent()) {
            Student student = studentId.get();
            student.setName(request.getName() != null ? request.getName() : student.getName());
            student.setPhone(request.getPhone() != null ? request.getPhone() : student.getPhone());
            student.setAddress(request.getAddress() != null ? request.getAddress() : student.getAddress());
            student.setSex(request.getSex() != null ? request.getSex() : student.getSex());
            student.setDateOfBirth(request.getDateOfBirth() != null ? request.getDateOfBirth() : student.getDateOfBirth());
            String urls = uploadService.uploadFileToServer(request.getFile());
            if (request.getFile() != null && !request.getFile().isEmpty()){
                urls = uploadService.uploadFileToServer(request.getFile());
            }
            student.setImageUrl(urls);
            studentRepository.save(student);
        }
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }
}

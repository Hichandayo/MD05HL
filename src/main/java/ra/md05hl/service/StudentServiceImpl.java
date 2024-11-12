package ra.md05hl.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.md05hl.model.dto.response.StudentCreateDto;
import ra.md05hl.model.entity.Student;
import ra.md05hl.repository.IStudentRepository;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
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
}

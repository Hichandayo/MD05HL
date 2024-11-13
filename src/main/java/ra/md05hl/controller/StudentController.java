package ra.md05hl.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.md05hl.model.dto.response.StudentCreateDto;
import ra.md05hl.model.dto.response.StudentEditDto;
import ra.md05hl.model.entity.Student;
import ra.md05hl.service.IStudentService;
import ra.md05hl.service.UploadService;

import java.io.IOException;

@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private UploadService uploadService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("list",studentService.findAll());
        return "student/list";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("studentAdd", new StudentCreateDto() );
        return "student/add";
    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("studentAdd") StudentCreateDto request, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("studentAdd",request);
            return "student/add";
        }
        studentService.add(request);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.findById(id).orElse(null);
        if (student != null) {
            StudentEditDto request = new StudentEditDto();
            request.setName(student.getName());
            request.setPhone(student.getPhone());
            request.setSex(student.getSex());
            request.setDateOfBirth(student.getDateOfBirth());
            request.setAddress(student.getAddress());
            request.setImageUrl(student.getImageUrl());

            model.addAttribute("studentEdit", request);
            return "student/edit";
        }

        return "redirect:/students"; // Nếu không tìm thấy sinh viên, chuyển hướng về danh sách
    }
    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable("id") Integer id,
                                @ModelAttribute("studentEdit") StudentEditDto request,
                                BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("studentEdit", request);
            return "student/edit"; // Nếu có lỗi, trả về form chỉnh sửa
        }

        // Giữ giá trị cũ cho những trường không thay đổi
        String urls = uploadService.uploadFileToServer(request.getFile()); // Giữ URL ảnh cũ nếu không có ảnh mới


        // Nếu có file ảnh mới, chúng ta sẽ lưu lại và cập nhật URL ảnh mới
        if (request.getFile() != null && !request.getFile().isEmpty()){
            urls = uploadService.uploadFileToServer(request.getFile());
        }

        // Cập nhật thông tin sinh viên với những trường đã thay đổi
        StudentEditDto updatedDto = new StudentEditDto();
        updatedDto.setName(request.getName());
        updatedDto.setPhone(request.getPhone());
        updatedDto.setSex(request.getSex());
        updatedDto.setDateOfBirth(request.getDateOfBirth());
        updatedDto.setAddress(request.getAddress());
        updatedDto.setImageUrl(urls);  // Cập nhật ảnh mới (hoặc giữ ảnh cũ)

        studentService.update(updatedDto, id);

        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "redirect:/";
    }
}

package dev.thewlabs.schoolar.domain.users;

import dev.thewlabs.schoolar.domain.admins.AdminService;
import dev.thewlabs.schoolar.domain.students.StudentService;
import dev.thewlabs.schoolar.domain.teachers.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdminService adminService;

    @Autowired
    public UserService(StudentService studentService, TeacherService teacherService, AdminService adminService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.adminService = adminService;
    }
}

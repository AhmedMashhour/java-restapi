package com.spring.example.demo.spring.example.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
private static StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
//    @Query("Select s from  Student s where s.email = ?1")

    public  static Student addStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent())
        {
            throw new IllegalStateException("Email Taken");
        }
        return studentRepository.save(student);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }else {
            throw new IllegalStateException("student " + id + " didn't exists !");
        }
    }

    @Transactional
    public Student updateStudent(Long id, String name , String email) {
        Optional<Student> studentExists =studentRepository.findById(id);
        if (studentExists.isPresent()){
            Student student = studentExists.get();
            if (name != null && !name.isEmpty())
                 student.setName(name);
            if (email != null &&!email.isEmpty())
                student.setEmail(email);
//            studentRepository.save(student);
            return student;
        }else {
            throw new IllegalStateException("student " +id +" didn't exists !");
        }

    }
}

package top.wisely.learningcaching.service;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import top.wisely.learningcaching.domain.model.Student;
import top.wisely.learningcaching.repository.StudentRepository;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "student")
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @CachePut(key = "#student.id")
    public Student saveOrUpdate(Student student){
        return studentRepository.save(student);
    }


    @CacheEvict
    public void remove(Long id){
        studentRepository.deleteById(id);
    }

    @Cacheable(unless = "#result == null")
    public Optional<Student> findOne(Long id){
        return studentRepository.findById(id);
    }


}

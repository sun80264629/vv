package com.vv.demo.spring.batch.work;

import com.vv.demo.spring.batch.bean.Student;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


public class MyWriter implements ItemWriter<Student> {
    /*@Autowired
    private StudentJpaRepository studentJpaRepository;*/
    @Override
    public void write(List<? extends Student> list) throws Exception {

        for (Student student:list) {
            //studentJpaRepository.save(student);
        }
    }
}

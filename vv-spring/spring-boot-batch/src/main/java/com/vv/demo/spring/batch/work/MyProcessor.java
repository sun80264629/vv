package com.vv.demo.spring.batch.work;

import com.vv.demo.spring.batch.bean.Person;
import com.vv.demo.spring.batch.bean.Student;
import org.springframework.batch.item.ItemProcessor;

public class MyProcessor implements ItemProcessor<Person, Student> {
    @Override
    public Student process(Person person) throws Exception {
        Student student=new Student();
        /*student.setName(person.getName());
        student.setAge(person.getAge());
        student.setAddress("无底洞");
        student.setNation(person.getNation());
        Random rand = new Random();
        student.setId(rand.nextInt(100)+"");*/
        return student;
    }
}

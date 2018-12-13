package com.vv.demo.spring.batch.work;

import com.vv.demo.spring.batch.bean.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyReader implements ItemReader<Person> {
    //@Autowired
   //private PersonJpaRepository personJpaRepository;
    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        /*List<Person> list=personJpaRepository.findAll();
        return list.get(0);*/
        return new Person();
    }
}

package com.vv.demo.design.factory;

import com.vv.demo.design.factory.creator.IWorkFactory;
import com.vv.demo.design.factory.creator.concrete.StudentWorkFactory;
import com.vv.demo.design.factory.creator.concrete.TeacherWorkFactory;

/**
 * <br>类 名: TestFactory 
 * <br>描 述: 工厂模式 
 * <br>作 者: Vv
 * <br>创 建： 2015年1月27日 
 * <br>版 本：v1.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class TestFactory {
	public static void main(String[] args) {
        IWorkFactory studentWorkFactory = new StudentWorkFactory();
        studentWorkFactory.getWork().doWork();
        
        IWorkFactory teacherWorkFactory = new TeacherWorkFactory();
        teacherWorkFactory.getWork().doWork();
    }
}

package com.vv.demo.design.factory.creator.concrete;

import com.vv.demo.design.factory.creator.IWorkFactory;
import com.vv.demo.design.factory.product.Work;
import com.vv.demo.design.factory.product.concrete.TeacherWork;

public class TeacherWorkFactory implements IWorkFactory {

	public Work getWork() {
		// TODO Auto-generated method stub
		return new TeacherWork();
	}

}

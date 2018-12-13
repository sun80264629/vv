package com.vv.demo.design.observer;

import com.vv.demo.design.observer.observer.Policeman;
import com.vv.demo.design.observer.observer.concrete.HuangPuPoliceman;
import com.vv.demo.design.observer.observer.concrete.TianHePoliceman;
import com.vv.demo.design.observer.subject.Citizen;
import com.vv.demo.design.observer.subject.concrete.HuangPuCitizen;
import com.vv.demo.design.observer.subject.concrete.TianHeCitizen;

public class TestObserver {
	public static void main(String[] args) {
		Policeman thPol = new TianHePoliceman();
		Policeman hpPol = new HuangPuPoliceman();

		Citizen citizen = new HuangPuCitizen(hpPol);
		citizen.sendMessage("unnormal");
		citizen.sendMessage("normal");

		System.out.println("===========");

		citizen = new TianHeCitizen(thPol);
		citizen.sendMessage("normal");
		citizen.sendMessage("unnormal");
	}

}

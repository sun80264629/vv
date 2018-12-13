package com.vv.demo.design.observer.subject.concrete;

import com.vv.demo.design.observer.observer.Policeman;
import com.vv.demo.design.observer.subject.Citizen;

public class TianHeCitizen extends Citizen {
	public TianHeCitizen(Policeman pol) {
		setPolicemen();
		register(pol);
	}

	@Override
	public void sendMessage(String help) {
		// TODO Auto-generated method stub
		setHelp(help);
        for (int i = 0; i < pols.size(); i++) {
            Policeman pol = (Policeman) pols.get(i);
            //通知警察行动
            pol.action(this);
        }

	}

}

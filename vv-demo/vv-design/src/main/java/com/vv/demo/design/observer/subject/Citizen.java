package com.vv.demo.design.observer.subject;

import java.util.ArrayList;
import java.util.List;

import com.vv.demo.design.observer.observer.Policeman;

public abstract class Citizen {
	
	protected List pols;

	protected String help = "normal";

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelp() {
		return this.help;
	}

	public abstract void sendMessage(String help);

	public void setPolicemen() {
		this.pols = new ArrayList();
	}

	public void register(Policeman pol) {
		this.pols.add(pol);
	}

	public void unRegister(Policeman pol) {
		this.pols.remove(pol);
	}

}

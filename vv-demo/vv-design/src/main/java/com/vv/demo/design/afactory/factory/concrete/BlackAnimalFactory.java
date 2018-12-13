package com.vv.demo.design.afactory.factory.concrete;

import com.vv.demo.design.afactory.factory.IAnimalFactory;
import com.vv.demo.design.afactory.product.ICat;
import com.vv.demo.design.afactory.product.IDog;
import com.vv.demo.design.afactory.product.concrete.BlackCat;
import com.vv.demo.design.afactory.product.concrete.BlackDog;

public class BlackAnimalFactory implements IAnimalFactory {

	public ICat createCat() {
		return new BlackCat();
	}

	public IDog createDog() {
		return new BlackDog();
	}

}

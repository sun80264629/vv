package com.vv.demo.design.afactory.factory.concrete;

import com.vv.demo.design.afactory.factory.IAnimalFactory;
import com.vv.demo.design.afactory.product.ICat;
import com.vv.demo.design.afactory.product.IDog;
import com.vv.demo.design.afactory.product.concrete.WhiteCat;
import com.vv.demo.design.afactory.product.concrete.WhiteDog;

public class WhiteAnimalFactory implements IAnimalFactory {

	public ICat createCat() {
		// TODO Auto-generated method stub
		return new WhiteCat();
	}

	public IDog createDog() {
		// TODO Auto-generated method stub
		return new WhiteDog();
	}

}

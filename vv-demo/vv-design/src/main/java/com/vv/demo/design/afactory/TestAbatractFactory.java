package com.vv.demo.design.afactory;

import com.vv.demo.design.afactory.factory.IAnimalFactory;
import com.vv.demo.design.afactory.factory.concrete.BlackAnimalFactory;
import com.vv.demo.design.afactory.factory.concrete.WhiteAnimalFactory;
import com.vv.demo.design.afactory.product.ICat;
import com.vv.demo.design.afactory.product.IDog;

public class TestAbatractFactory {
	public static void main(String[] args) {
	    IAnimalFactory blackAnimalFactory = new BlackAnimalFactory();
	    ICat blackCat = blackAnimalFactory.createCat();
	    blackCat.eat();
	    IDog blackDog = blackAnimalFactory.createDog();
	    blackDog.eat();
	    
	    IAnimalFactory whiteAnimalFactory = new WhiteAnimalFactory();
	    ICat whiteCat = whiteAnimalFactory.createCat();
	    whiteCat.eat();
	    IDog whiteDog = whiteAnimalFactory.createDog();
	    whiteDog.eat();
	}

}

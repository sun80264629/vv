package com.vv.demo.design.afactory.factory;

import com.vv.demo.design.afactory.product.ICat;
import com.vv.demo.design.afactory.product.IDog;

public interface IAnimalFactory {
	ICat createCat();
	IDog createDog();
}

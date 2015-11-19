package controllers;

import java.util.Date;
import java.util.List;
import models.Shoes;
import models.User;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.With;

public class Sell extends Application {
	public static void sell() {
		render();
	}
	public static void add() {
		render();
	}

	public static void list() {
		List<Shoes> shoes = Shoes.findAll();
		render(shoes);
	}

	public static void add(String gender, String subject, String brand,
			String neworold, String prise, String size, String detail,
			String province, String area) {
		if (gender == "-------------- เลือกเพศ --------------") {
			render();
		} else {
			Shoes shoes = new Shoes();
			shoes.gender = gender;
			shoes.brand = brand;
			shoes.neworold = neworold;
			shoes.brand = brand;
			shoes.neworold = neworold;
			shoes.createdDate = new Date();
			shoes.updatedDate = new Date();
			shoes.insert();
		}
	}
}
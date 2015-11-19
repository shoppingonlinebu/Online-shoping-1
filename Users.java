package controllers;

import play.libs.*;
import play.data.validation.*;
import java.util.Date;
import java.util.List;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Users extends Controller {
	public static void index(int currentPage) {
		boolean hasNext = false;
		boolean hasPrevious = false;
		int limit = 3;
		int allPage = (int) Math
				.ceil(((double) (User.all().count() / (double) limit)));
		if (currentPage == 0 || (currentPage > 1 && currentPage > allPage)) {
			redirect("/users/list/1");
		}
		int offset = (currentPage - 1) * limit;
		List<User> users = User.all().fetch(limit, offset);
		if (currentPage > 1) {
			hasPrevious = true;
		}
		if (currentPage < allPage) {
			hasNext = true;
		}
		renderTemplate("Users/list.html", users, allPage, currentPage, hasNext,
				hasPrevious);
	}

	public static void list() {
		List<User> users = User.findAll();
		render(users);
	}

	public static void add() {
		render();
	}

	public static void create(@Valid User user) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			redirect("/users/add");
		} else {
			user.passwd = Crypto.passwordHash(user.passwd);
			user.save();
			flash.success("Your account is successfullyregistered");
			redirect("/users/list");
		}
	}

	public static void edit(Long id) {
		User user = User.findById(id);
		render(user);
	}

	public static void update(@Valid User user) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			redirect("/users/edit/" + user.id);
		} else {
			user.passwd = Crypto.passwordHash(user.passwd);
			user.update();
			flash.success("Your account is successfully registered");
			redirect("/users/list");
		}
	}

	public static void delete(Long id) {
		notFoundIfNull(id);
		int deleteUser = User.deleteById(id);
		list();
	}

	public static void show(Long id) {
		User user = User.findById(id);
		render(user);
	}

	public static void add(String name, String passwd, String fullname, String lastname, String call, String fbid, String lineid, String email) {
		if (name == null || name.isEmpty()) {
			render();
		} else {
			User user = new User();
			user.name = name;
			user.passwd = passwd;
			user.fullname = fullname;
			user.lastname = lastname;
			user.call = call;
			user.fbid = fbid;
			user.lineid = lineid;
			user.email = email;
			user.createdDate = new Date();
			user.updatedDate = new Date();
			user.insert();
			Users.list();
		}
	}
}
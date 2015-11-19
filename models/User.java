package models;

import play.*;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import siena.*;
import java.util.*;
import helpers.Gravatar;

@Entity
public class User extends Model {
	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("name")
	@NotNull
	@Required(message = "name.required")
	@MaxSize(value = 50, message = "name.maxsize")
	public String name;
	@Column("passwd")
	@Password
	@Required(message = "passwd.required")
	@MinSize(value = 6, message = "passwd.minsize")
	public String passwd;
	
	@Column("fullname")
	@NotNull
	@Required(message = "fullname.required")
	@MaxSize(value = 50, message = "fullname.maxsize")
	public String fullname;
	@Column("lastname")
	@NotNull
	@Required(message = "lastname.required")
	@MaxSize(value = 50, message = "lastname.maxsize")
	public String lastname;
	@Column("call")
	@NotNull
	@Required(message = "call.required")
	@MaxSize(value = 50, message = "call.maxsize")
	public String call;
	@Column("fbid")
	@NotNull
	@Required(message = "fbid.required")
	@MaxSize(value = 50, message = "fbid.maxsize")
	public String fbid;
	@Column("lineid")
	@NotNull
	@Required(message = "lineid.required")
	@MaxSize(value = 50, message = "lineid.maxsize")
	public String lineid;
	@Column("email")
	@NotNull
	@Required(message = "email.required")
	@Email(message = "email.valid")
	public String email;

	@Column("createdDate")
	@NotNull
	public Date createdDate;

	@Column("updatedDate")
	@NotNull
	public Date updatedDate;

	public static Query<User> all() {
		return Model.all(User.class);
	}

	public static List<User> findAll() {
		return all().order("createdDate").fetch();
	}

	public static User findById(Long id) {
		return all().filter("id", id).get();
	}

	public String getGravatarURL() {
		Gravatar gra_ctrl = new Gravatar();
		String gravatar_id = "";
		if (this.email != null) {
			gravatar_id = gra_ctrl.md5Hex(this.email);
		}
		String gravatar_url = "http://secure.gravatar.com/avatar/"
				+ gravatar_id;
		return gravatar_url;
	}

	public static int deleteById(Long id) {
		return all().filter("id", id).delete();
	}

	public static User find_by_id(Long id) {
		return all().filter("id", id).get();
	}
	
	public static User find_by_email(String email) {
		return all().filter("email", email).get();
	}
}

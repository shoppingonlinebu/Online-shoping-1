package models;

import play.*;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import siena.*;
import java.util.*;
import helpers.Gravatar;

@Entity
public class Shoes extends Model {
	@Id(Generator.AUTO_INCREMENT)
	public Long id;

	@Column("gender")
	@NotNull
	@Required(message = "gender.required")
	@MaxSize(value = 50, message = "gender.maxsize")
	public String gender;
	@Column("subject")
	@NotNull
	@Required(message = "subject.required")
	@MaxSize(value = 50, message = "subject.maxsize")
	public String subject;
	@Column("brand")
	@NotNull
	@Required(message = "brand.required")
	@MaxSize(value = 50, message = "brand.maxsize")
	public String brand;
	@Column("neworold")
	@NotNull
	@Required(message = "neworold.required")
	@MaxSize(value = 50, message = "neworold.maxsize")
	public String neworold;
	@Column("prise")
	@NotNull
	@Required(message = "prise.required")
	@MaxSize(value = 50, message = "prise.maxsize")
	public String prise;
	@Column("size")
	@NotNull
	@Required(message = "size.required")
	@MaxSize(value = 50, message = "size.maxsize")
	public String size;
	
	@Column("detail")
	@NotNull
	@Required(message = "detail.required")
	@MaxSize(value = 500, message = "detail.maxsize")
	public String detail;
	@Column("province")
	@NotNull
	@Required(message = "province.required")
	@MaxSize(value = 50, message = "province.maxsize")
	public String province;
	@Column("area")
	@NotNull
	@Required(message = "area.required")
	@MaxSize(value = 50, message = "area.maxsize")
	public String area;
	
	
	@Column("createdDate")
	@NotNull
	public Date createdDate;

	@Column("updatedDate")
	@NotNull
	public Date updatedDate;

	public static Query<Shoes> all() {
		return Model.all(Shoes.class);
	}

	public static List<Shoes> findAll() {
		return all().order("createdDate").fetch();
	}

	public static Shoes findById(Long id) {
		return all().filter("id", id).get();
	}


	public static int deleteById(Long id) {
		return all().filter("id", id).delete();
	}

	public static Shoes find_by_id(Long id) {
		return all().filter("id", id).get();
	}
	
}


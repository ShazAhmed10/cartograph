package dev.shaz.productservice;

import dev.shaz.productservice.Inheritanceexample.joinedclass.MentorRepository;
import dev.shaz.productservice.Inheritanceexample.joinedclass.Student;
import dev.shaz.productservice.Inheritanceexample.joinedclass.StudentRepository;
import dev.shaz.productservice.Inheritanceexample.singletable.Mentor;
import dev.shaz.productservice.Inheritanceexample.tableperclass.User;
import dev.shaz.productservice.Inheritanceexample.tableperclass.UserRepository;
import dev.shaz.productservice.models.Category;
import dev.shaz.productservice.models.Price;
import dev.shaz.productservice.models.Product;
import dev.shaz.productservice.repositories.CategoryRepository;
import dev.shaz.productservice.repositories.PriceRepository;
import dev.shaz.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	//private MentorRepository mentorRepository;
	//private StudentRepository studentRepository;

	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;
	private PriceRepository priceRepository;

	@Autowired
	public ProductServiceApplication(CategoryRepository categoryRepository, ProductRepository productRepository, PriceRepository priceRepository){
		//this.mentorRepository = mentorRepository;
		//this.studentRepository = studentRepository;

		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		*** To show Inheritance Examples working ***

//		Mentor mentor = new Mentor();
//		mentor.setName("Jackson");
//		mentor.setEmail("jack@gmail.com");
//		mentor.setAvgRating(90.0);
//
//		mentorRepository.save(mentor);

//		User user = new User();
//		user.setId(1L);
//		user.setName("Shaz");
//		user.setEmail("shaz@gmail.com");
//
//		userRepository.save(user);
//
//		Student student = new Student();
//		student.setId(2L);
//		student.setName("Mark");
//		student.setEmail("mark@gmail.com");
//		student.setBatch("batch 1");
//		student.setPsp(88.0);
//
//		studentRepository.save(student);

//		Student student = new Student();
//		student.setName("Anna");
//		student.setEmail("anna@gmail.com");
//		student.setBatch("batch 1");
//		student.setPsp(90.0);
//
//		studentRepository.save(student);

//		******

//		Category category = new Category();
//		category.setName("Electronics");
//		Category savedCategory = categoryRepository.save(category);

//		Price price = new Price("Rupee", 10.0);
//		Price savedPrice = priceRepository.save(price);

//		Product product = new Product();
//		product.setTitle("Samsung");
//		product.setDescription("2nd best phone");
//		product.setImage("image url samsung");
//		product.setCategory(category);
//		product.setPrice(price);
//		productRepository.save(product);

	}
}

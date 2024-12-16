package com.cap.MyLearning;


import com.cap.MyLearning.repository.CourseDetailsRepository;
import com.cap.MyLearning.repository.CourseRepository;
import com.cap.MyLearning.repository.UserRepository;
import com.cap.MyLearning.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class MyLearningApplication implements CommandLineRunner {
	private final CourseRepository courseRepository;
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final CourseDetailsRepository courseDetailsRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
    public MyLearningApplication(CourseRepository courseRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, CourseDetailsRepository courseDetailsRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.courseDetailsRepository = courseDetailsRepository;
    }


    public static void main(String[] args) {
		SpringApplication.run(MyLearningApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {



//		UserRole adminRole = new UserRole();
//		adminRole.setRoleName("Admin");
//		userRoleRepository.save(adminRole);
//
//		UserRole studentRole = new UserRole();
//		studentRole.setRoleName("Student");
//		userRoleRepository.save(studentRole);
//
//		UserRole backOfficeRole = new UserRole();
//		backOfficeRole.setRoleName("Back Office");
//		userRoleRepository.save(backOfficeRole);
//
//		User admin = new User(0L,"Mila","Bom","Mila@Bom.com","123123123", encoder.encode("123")
//				,userRoleRepository.getReferenceById(1L),"Developer", new ArrayList<>());
//		userRepository.save(admin);
//
//		User student = new User(0L,"Student","Bom","always@student.com","123123123", encoder.encode("123")
//				,userRoleRepository.getReferenceById(2L),"Scrum", new ArrayList<>());
//		userRepository.save(student);
//
//		User backOffice = new User(0L,"Gabriel","Moawad","back@office.com","123123123", encoder.encode("123")
//				,userRoleRepository.getReferenceById(3L),"Tester", new ArrayList<>());
//		userRepository.save(backOffice);
//		List<User> students = userRepository.findAll();
//		//courseList.add(courseRepository.getReferenceById(2L));

 		//Roller oluşturuluyor
//		UserRole adminRole = new UserRole();
//		adminRole.setRoleName("Admin");
//		userRoleRepository.save(adminRole);
//
//		UserRole backOfficeRole = new UserRole();
//		backOfficeRole.setRoleName("Back Office");
//		userRoleRepository.save(backOfficeRole);
//
//		UserRole studentRole = new UserRole();
//		studentRole.setRoleName("Student");
//		userRoleRepository.save(studentRole);
//
//		// Admin Kullanıcısı
//		User admin = new User();
//		admin.setUserName("adminUser");
//		admin.setLastName("Admin");
//		admin.setEmail("Mila@Bom.com");
//		admin.setPhone("123456789");
//		admin.setPassword(encoder.encode("123"));  // Şifre hashing yapılabilir.
//		admin.setRole(adminRole);
//		admin.setDepartment("Administration");
//		userRepository.save(admin);
//
//		// Back Office Kullanıcısı
//		User backOfficeUser = new User();
//		backOfficeUser.setUserName("backOfficeUser");
//		backOfficeUser.setLastName("BackOffice");
//		backOfficeUser.setEmail("back@office.com");
//		backOfficeUser.setPhone("987654321");
//		backOfficeUser.setPassword(encoder.encode("123"));
//		backOfficeUser.setRole(backOfficeRole);
//		backOfficeUser.setDepartment("Operations");
//		userRepository.save(backOfficeUser);
//
//		// Student Kullanıcısı
//		User studentUser = new User();
//		studentUser.setUserName("studentUser");
//		studentUser.setLastName("Student");
//		studentUser.setEmail("always@student.com");
//		studentUser.setPhone("456123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(studentRole);
//		studentUser.setDepartment("Science");
//		userRepository.save(studentUser);
//
//		// Kurslar oluşturuluyor
//		Course course1 = new Course();
//		course1.setCourseName("Java Programming");
//		course1.setDescription("Learn Java from scratch.");
//		course1.setDayPartsOfCourse("Morning");
//		course1.setOnline(true);
//		course1.setMaterialLanguage("English");
//		course1.setSpokenLanguage("English");
//		course1.setLocation("Online");
//		course1.setImage("https://logos-world.net/wp-content/uploads/2022/07/Java-Logo.png");
//		courseRepository.save(course1);
//
//		Course course2 = new Course();
//		course2.setCourseName("Angular Development");
//		course2.setDescription("Frontend development with Angular.");
//		course2.setDayPartsOfCourse("Afternoon");
//		course2.setOnline(true);
//		course2.setMaterialLanguage("English");
//		course2.setSpokenLanguage("English");
//		course2.setLocation("Online");
//		course2.setImage("https://platri.de/wp-content/uploads/2024/01/Angular-Framework-e1649312852136.png");
//		courseRepository.save(course2);
//
//		Course course3 = new Course();
//		course3.setCourseName("Spring Boot Mastery");
//		course3.setDescription("Master Spring Boot framework.");
//		course3.setDayPartsOfCourse("Evening");
//		course3.setOnline(false);
//		course3.setMaterialLanguage("English");
//		course3.setSpokenLanguage("English");
//		course3.setLocation("In-Person");
//		course3.setImage("https://www.amigoscode.com/assets/thumbnails/courses/spring-boot-master-class.webp");
//		courseRepository.save(course3);
//
//		// Kurs detayları oluşturuluyor ve öğrenci kursa ekleniyor
//		CourseDetails courseDetails1 = new CourseDetails();
//		courseDetails1.setCourse(course1);
//		courseDetails1.setStartDate("2024-10-10");
//		courseDetails1.setEndDate("2024-12-12");
//		courseDetails1.setStartTime("09:00 AM");
//		courseDetails1.setEndTime("12:00 PM");
//		courseDetails1.setTeacherName("John Doe");
//		courseDetails1.setMaxStudents(20);
//		courseDetails1.setCompleted(false);
//		courseDetails1.getStudents().add(studentUser); // Öğrenciyi kursa ekle
//		courseDetailsRepository.save(courseDetails1);
//
//		CourseDetails courseDetails2 = new CourseDetails();
//		courseDetails2.setCourse(course2);
//		courseDetails2.setStartDate("2024-11-05");
//		courseDetails2.setEndDate("2024-12-12");
//		courseDetails2.setStartTime("01:00 PM");
//		courseDetails2.setEndTime("04:00 PM");
//		courseDetails2.setTeacherName("Jane Smith");
//		courseDetails2.setMaxStudents(25);
//		courseDetails2.setCompleted(false);
//		courseDetails2.getStudents().add(studentUser); // Öğrenciyi kursa ekle
//		courseDetailsRepository.save(courseDetails2);
//
//		CourseDetails courseDetails3 = new CourseDetails();
//		courseDetails3.setCourse(course3);
//		courseDetails3.setStartDate("2024-12-01");
//		courseDetails3.setEndDate("2024-12-12");
//		courseDetails3.setStartTime("06:00 PM");
//		courseDetails3.setEndTime("09:00 PM");
//		courseDetails3.setTeacherName("Michael Brown");
//		courseDetails3.setMaxStudents(15);
//		courseDetails3.setCompleted(false);
//		courseDetailsRepository.save(courseDetails3);

		//1.Student
//		User studentUser = new User();
//		studentUser.setUserName("Emma");
//		studentUser.setLastName("Davis");
//		studentUser.setEmail("altijd@student.com");
//		studentUser.setPhone("456123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Software");
//		userRepository.save(studentUser);

		//2.Student
//		studentUser.setUserName("Liam");
//		studentUser.setLastName("Johnson");
//		studentUser.setEmail("liamd@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Software");
//		userRepository.save(studentUser);
//
//		//3.Student
//		studentUser.setUserName("Mark");
//		studentUser.setLastName("Luna");
//		studentUser.setEmail("markd@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Software");
//		userRepository.save(studentUser);
//
//		//4.Student
//		studentUser.setUserName("Olivia");
//		studentUser.setLastName("MArtinez");
//		studentUser.setEmail("olivia@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Software");
//		userRepository.save(studentUser);
//
//		//5.Student
//		studentUser.setUserName("Noah");
//		studentUser.setLastName("Miller");
//		studentUser.setEmail("noah@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Software");
//		userRepository.save(studentUser);
//
//		//6.Student
//		studentUser.setUserName("Lucas");
//		studentUser.setLastName("Anderson");
//		studentUser.setEmail("lucas@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Data Science");
//		userRepository.save(studentUser);
//
//		//7.Student
//		studentUser.setUserName("Isabella");
//		studentUser.setLastName("Wilson");
//		studentUser.setEmail("isabella@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Data Science");
//		userRepository.save(studentUser);
//
//		//8.Student
//		studentUser.setUserName("Eva");
//		studentUser.setLastName("Thomas");
//		studentUser.setEmail("eva@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Data Science");
//		userRepository.save(studentUser);
//
//		//9.Student
//		studentUser.setUserName("James");
//		studentUser.setLastName("Watson");
//		studentUser.setEmail("james@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Data Science");
//		userRepository.save(studentUser);
//
//		//10.Student
//		studentUser.setUserName("Jennifer");
//		studentUser.setLastName("White");
//		studentUser.setEmail("jennifer@student.com");
//		studentUser.setPhone("4562123789");
//		studentUser.setPassword(encoder.encode("123"));
//		studentUser.setRole(userRoleRepository.getReferenceById(3L));
//		studentUser.setDepartment("Data Science");
//		userRepository.save(studentUser);


	}


}

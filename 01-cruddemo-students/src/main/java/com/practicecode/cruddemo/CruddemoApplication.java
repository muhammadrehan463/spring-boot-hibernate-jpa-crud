package com.practicecode.cruddemo;

import com.practicecode.cruddemo.dao.StudentDAO;
import com.practicecode.cruddemo.dao.StudentDAOImpl;
import com.practicecode.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAll(studentDAO);
		};
	}

	public void createStudent(StudentDAO studentDAO){
		//create student object
		System.out.println("Createing new student object..");
		Student studentObj = new Student("Adeel", "Khalid", "adeel@gmail.com");

		//save student object
		System.out.println("Saving student data....");
		studentDAO.save(studentObj);

		//displiay data of saved student
		System.out.println("Student Saved...");
		System.out.println("Id: " + studentObj.getId());
		System.out.println("First Name: " + studentObj.getFirstName());
		System.out.println("Last Name: " + studentObj.getLastName());
		System.out.println("Email: " + studentObj.getEmail());
	}

	public void readStudent(StudentDAO studentDAO){
		//create student object
		System.out.println("Createing new student object..");
		Student studentObj = new Student("ALi", "Khan", "ali@gmail.com");

		//save student object
		System.out.println("Saving student data....");
		studentDAO.save(studentObj);

		//displiay data of saved student
		System.out.println("Student Saved...");
		System.out.println("Id: " + studentObj.getId());
		System.out.println("First Name: " + studentObj.getFirstName());
		System.out.println("Last Name: " + studentObj.getLastName());
		System.out.println("Email: " + studentObj.getEmail());

		//finding student by id
		Student tempStudent = studentDAO.findById(studentObj.getId());
		System.out.println("Found the student record: " + tempStudent);
	}

	public void queryForStudents(StudentDAO studentDAO){
		List<Student> students = studentDAO.findAll();

		for(Student std: students){
			System.out.println(std);
		}
	}

	public void queryForStudentsByLastName(StudentDAO studentDAO){
		List<Student> theStudent = studentDAO.findByLastName("Khan");

		for (Student tempStudent: theStudent){
			System.out.println(tempStudent);
		}
	}

	public void updateStudent(StudentDAO studentDAO){
		Student myStudent = studentDAO.findById(1);

		myStudent.setFirstName("Qasim");
		studentDAO.update(myStudent);

		System.out.println(myStudent);
	}

	public void deleteStudent(StudentDAO studentDAO){
		studentDAO.delete(10);

		System.out.println("Data deleted..");
	}

	public void deleteAll(StudentDAO studentDAO){
		int numOfRows = studentDAO.deleteAllStudents();
		System.out.println(numOfRows + " " + "number of rows deleted.. ");
	}

}

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.globits.config.DatabaseConfig;
import com.globits.sample.domain.Student;
import com.globits.sample.dto.StudentDto;
import com.globits.sample.repository.StudentRepository;
import com.globits.sample.service.StudentService;
import com.globits.sample.utils.ImportExcel;
import com.globits.security.dto.UserDto;
import com.globits.security.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseConfig.class)
//@Transactional(propagation = Propagation.REQUIRED)
public class ServiceTest {

	@Autowired
	StudentRepository studentRepository;
	
	
	@Autowired
	StudentService service;
	@Test
	public void saveStudentList() throws FileNotFoundException {
		FileInputStream f = new FileInputStream("D:\\Projects\\Training\\globits-v2\\sample-project-v2\\sample-project-v2-app\\Student.xlsx");//Doi duong dan o day
		List<StudentDto> list = ImportExcel.importStudent(f);
		for(StudentDto s:list) {
			service.saveStudent(s);
		}
	}
	
	//@Test
	public void repoSaveStudent() {
		Student s = new Student();
		s.setFirstName("Test Repo");
		studentRepository.save(s);
	}
	
	//@Test
	public void saveServiceStudent() {
		Student s = new Student();
		s.setFirstName("Test Service");
		service.save(s);
	}
	//@Test
	public void testQueryStudent() {
		Page<StudentDto> page = service.sqlQueryByStudentClass(1, 10,"%N%");
		System.out.println(page.getTotalElements());
	}
}

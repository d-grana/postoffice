package practica2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import practica2.Form;

public interface FormRepository extends JpaRepository<Form, Long> {
	
	List<Form> findByName(String name);

}

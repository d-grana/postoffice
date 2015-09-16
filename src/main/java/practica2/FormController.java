package practica2;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import practica2.FormRepository;


@Controller
public class FormController {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${my.property}")
	private String myProperty;
	
	@Autowired
    private FormRepository repository;

	@RequestMapping("/")
    public String sendForm(Form form, Model model) throws URISyntaxException {

		if((form.getStreet() != null) && (form.getCity() != null) && (form.getCountry() != null)){
			Address address = new Address(form.getStreet(),form.getCity(),form.getCountry());
			URI url = new URI(myProperty);       
	        String postalCode = restTemplate.postForObject(url, address, String.class);
	        form.setPostalCode(postalCode);
			repository.save(form);
		}

        return "sendForm";
    }
	
	@RequestMapping("/search")
    public String search(Model model) {

        return "search";
    }
	
	@RequestMapping("/board")
    public String tablon(String name, Model model) {

		model.addAttribute("forms", repository.findByName(name));

        return "board";
    }
}
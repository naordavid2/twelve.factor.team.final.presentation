package demo.microservice.three.factors.of.twelve;

import java.util.List;

public interface ManagementService {

	public List<Reader> getAllReaders(int size, int page);

	public Reader createReader(String id, String email, String firstName, String lastName);

}

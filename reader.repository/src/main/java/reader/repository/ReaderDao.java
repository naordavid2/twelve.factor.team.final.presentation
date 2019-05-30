package reader.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReaderDao extends 
	PagingAndSortingRepository	<Reader, String>{
}

package reader.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReaderServiceImpl implements ReaderService {
	private ReaderDao readers;

	@Autowired
	public ReaderServiceImpl(ReaderDao readers) {
		super();
		this.readers = readers;
	}

	@Override
	@Transactional(readOnly = true)
	public Reader getReaderById(String id) {
		return this.readers.findById(id)
				.orElseThrow(()->new ReaderNotFoundException("no reader for id: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reader> getAllReaders(int size, int page) {
		return this.readers
					.findAll(
							PageRequest.of(
									page, 
									size, 
									Direction.ASC, 
									"lastName", "firstName", "id"))
					.getContent();
	}

	@Override
	@Transactional
	public Reader writeReader(Reader reader) {
		return this.readers
				.save(reader);
	}

	@Override
	@Transactional
	public void deleteAll() {
		this.readers.deleteAll();
	}

}

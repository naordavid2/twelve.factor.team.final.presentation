package reader.repository;

import java.util.List;

public interface ReaderService {
	public Reader getReaderById (String id);
	public List<Reader> getAllReaders (int size, int page);
	public Reader writeReader (Reader reader);
	public void deleteAll();
}

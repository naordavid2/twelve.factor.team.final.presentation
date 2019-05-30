package reader.repository;

public class ReaderNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8162859257233006422L;

	public ReaderNotFoundException() {
	}

	public ReaderNotFoundException(String message) {
		super(message);
	}

	public ReaderNotFoundException(Throwable cause) {
		super(cause);
	}

	public ReaderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}

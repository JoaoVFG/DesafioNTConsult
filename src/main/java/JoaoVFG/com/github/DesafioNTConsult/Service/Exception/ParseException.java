package JoaoVFG.com.github.DesafioNTConsult.Service.Exception;

public class ParseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParseException (String mensagem) {
        super(mensagem);
    }

    public ParseException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}

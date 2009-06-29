package dondeando.excepciones;

/**
 * Versión más detallada de  NullPointerException que contiene
 * información sobre qué argumento era null.
 *
 * @author  <a href="mailto:jmarjona@hp-cda.com">Juanma Arjona</a>
 */
public class DetailedNullPointerException extends NullPointerException {

    private static final long serialVersionUID = -8593304659090818385L;
    private final String argumentName_;


    /**
     * Create an instance
     *
     * @param argumentName The name of the argument that was null
     * @param message The message to use in the exception
     */
    public DetailedNullPointerException( final String argumentName, final String message ) {

        super(message);
        argumentName_ = argumentName;
    }


    /**
     * Create an instance
     *
     * @param argumentName The name of the argument that was null
     */
    public DetailedNullPointerException( final String argumentName ) {
        this( argumentName, argumentName );
    }


    /**
     * Return the name of the argument that was null.
     * @return The name of the argument
     */
    public String getArgumentName() {
        return argumentName_;
    }
}


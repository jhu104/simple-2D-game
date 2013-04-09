package core.exceptions;

/**
 *
 * @author jay
 */
public class PatchException extends Exception {
    public PatchException (char c) {
        _char = c;
    }

    public String toString () {
        return "Invalid Patch character: " + _char;
    }

    private char _char;
}

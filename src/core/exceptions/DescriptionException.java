package core.exceptions;

/**
 * DescriptionException thrown when setting up a world instance
 * if the description does not match the required world size.
 *
 * @author jay
 */
public class DescriptionException extends Exception {
    public DescriptionException (int descriptionLength, int worldColumns) {
        _descriptionLength = descriptionLength;
        _worldColumns = worldColumns;
    }

    public String toString () {
        return "Description length is " + _descriptionLength + 
                " and not " + _worldColumns;
    }

    private int _descriptionLength;
    private int _worldColumns;
}

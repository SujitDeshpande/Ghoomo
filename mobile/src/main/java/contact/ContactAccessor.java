package contact;

import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.List;

/**
 * Created by kuldeep on 10/09/16.
 */
public interface ContactAccessor{

        /** Projection to use when querying contacts */
        public static final String[] PROJECTION_CONTACTS_SUMMARY = new String[] {
                ContactsContract.Contacts._ID, // 0
                ContactsContract.Contacts.DISPLAY_NAME, // 1
                ContactsContract.Contacts.LOOKUP_KEY, // 2
        };

        /** Index of contact id column */
        static final int SUMMARY_ID_COLUMN_INDEX = 0;

        /** Index of contact name column */
        static final int SUMMARY_NAME_COLUMN_INDEX = 1;

        /** Index of contact lookup-key column */
        static final int SUMMARY_LOOKUP_KEY_COLUMN_INDEX = 2;

public class ContactIdAndName {
    public int id;
    public String name;
}

    /**
     * Gets a cursor for all (visible) contacts
     *
     * @return a cursor for all contacts
     */
    public Cursor getAllContacts();

    public Cursor getFavoriteContacts();

    public Cursor getContactsByConstraint(String constraint);

    /**
     * Gets a cursor for one contact by the contact id.
     *
     * @return a cursor for one contact.
     */
    public Cursor getContact(long contactId);

//    /**
//     * Gets an intent used for picking a contact
//     *
//     * @return the intent to use
//     */
//    public Intent getIntentForPickContact();
//
//    /**
//     * Gets an intent used for showing contact info
//     *
//     * @param contactId the contact id
//     * @return the intent to use
//     */
//    public Intent getIntentForContactDetails(long contactId);

//    /**
//     * Gets an intent used for showing details about the supplied number. This is much like the
//     * contact details intent, but for unknown numbers.
//     *
//     * @param number the number
//     * @return the intent to use
//     */
//    public Intent getIntentForNumberDetails(String number);

    // /**
    // * Gets an intent used for choosing a single number from supplied contact
    // * id.
    // *
    // * @param contactId the contact id
    // * @param contactName the name of the contact
    // * @param highlightNumber number to highlight, can be null
    // * @return the intent to use
    // */
    // public Intent getIntentForFavorites(Context context, long contactId, String contactName);
    //
    // /**
    // * Gets an intent used for choosing a single number from supplied contact
    // * id.
    // *
    // * @param contactId the contact id
    // * @param contactName the name of the contact
    // * @param highlightNumber number to highlight, can be null
    // * @return the intent to use
    // */
    // public Intent getIntentForChoosingNumber(Context context, long contactId, String
    // contactName);

    /**
     * Gets contact id from supplied number. This number should be in the international format.
     *
     * @param number the number to look up
     * @return the contact id, or -1 if not found.
     */
    public int getContactIdFromNumber(String number);

    /**
     * Gets contact id and name from supplied number. This number should be in the international
     * format.
     *
     * @param number the number to look up
     * @return an instance of ContactIdAndName, if the number is not in the contact book, the "id"
     *         will be -1 and the "name" will be input, like "+4673920111".
     */
    public ContactIdAndName getContactIdAndNameFromNumber(String number);

    /**
     * Gets a list of all contact id's that are associated with supplied <code>number</code>.
     *
     * @param number the number to search for
     * @return A list of contact id's matching the number
     */
    public List<Long> getContactIdsFromNumber(String number);

    /**
     * Gets if provided number exists for the supplied contact id. This method is to be used for
     * matching a specific number to a specific contact id.
     *
     * @param number the number to search for
     * @param contactId the contact id to search for
     * @return true, if supplied contact has provided number
     */
    public boolean isNumberInContact(String number, long contactId);

    /**
     * Gets contact name from supplied number, if found in contacts. This number should be in the
     * international format.
     *
     * @param number the number to look up
     * @return the contact's name, or null if not found.
     */
    public String getContactNameFromNumber(String number);

//    /**
//     * Gets contact name for supplied contact id, if found in contacts.
//     *
//     * @param contactId the contact id
//     * @return the contact's name, or null if not found.
//     */
//    public String getContactNameFromId(long contactId);
//
//    /**
//     * Get contact number for supplied contact id
//     *
//     * @param contactId the contact id
//     * @return the contact number
//     */
//    public List<String> getContactNumbersFromId(long contactId);

    /**
     * Get cursor for all numbers stored for supplied contact id
     *
     * @param contactId the contact to look up
     * @return A cursor for all contact numbers
     */
    public Cursor getContactNumbers(long contactId);

//    /**
//     * Get a contacts photo as a Bitmap
//     *
//     * @param contactId the contact id
//     * @return the contacts photo as a bitmap, or null if not found
//     */
//    public Bitmap getContactBitmap(long contactId);

}


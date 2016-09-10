package contact;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;

import com.ghoomo.ghoomo.ui.GhoomoApplication;

/**
 * Implementation to give access to contact data
 *
 * @author emil.olsson@connecta.se
 */
public class ContactAccessorImpl implements ContactAccessor {
    private static final String TAG = ContactAccessorImpl.class.getSimpleName();

    // protected static final String TAG =
    // ContactAccessorImpl.class.getSimpleName();

    /**
     * Selection for all contacts that are visible and that has a number
     */
    // protected static final String SELECTION_VISIBLE_WITH_NUMBER =
    // ContactsContract.Contacts.IN_VISIBLE_GROUP + "='1' AND "
    // + ContactsContract.Contacts.HAS_PHONE_NUMBER + "='1'";

    protected static final String SELECTION_VISIBLE_WITH_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER
            + "='1'";
    protected static final String SELECTION_FAVORITES_ANDV_ISIBLE_WITH_NUMBER_ = ContactsContract.Contacts.HAS_PHONE_NUMBER
            + "='1' and " + ContactsContract.Contacts.STARRED + "='1'";
    protected Context context;

    // On some device like Hisense U939 (ZA), querying ContactsContract.Contacts.CONTENT_URI returns
    // empty cursor, but querying
    // android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI works.
    // So on such devices, we need to use Phone.CONTENT_URI to mimic the Contacts.CONTENT_URI.
    private boolean mShouldMimicContacts;

    public ContactAccessorImpl(Context context) {
        this.context = context;
    }

    // @Override
    // public Intent getIntentForPickContact() {
    // return new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
    // }

    // @Override
    // public Intent getIntentForContactDetails(long contactId) {
    // return new Intent(Intent.ACTION_VIEW, Uri.withAppendedPath(
    // ContactsContract.Contacts.CONTENT_URI, "" + contactId));
    // }

    // @Override
    // public Intent getIntentForNumberDetails(String number) {
    // Intent intent = new Intent(Intent.ACTION_INSERT,
    // ContactsContract.Contacts.CONTENT_URI);
    //
    // intent.putExtra(ContactsContract.Intents.Insert.PHONE, number);
    //
    // return intent;
    // }

    // @Override
    // public Intent getIntentForChoosingNumber(Context context, long contactId,
    // String contactName) {
    // Intent intent = new Intent(context, ContactsDetailScreen.class);
    //
    // intent.putExtra(ChooseNumberActivity.EXTRA_CONTACT_ID, contactId);
    // intent.putExtra(ChooseNumberActivity.EXTRA_CONTACT_NAME, contactName);
    //
    // return intent;
    // }

    // /* (non-Javadoc)
    // * @see
    // com.freephoo.android.contacts.ContactAccessor#getIntentForFavorites(android.content.Context,
    // long, java.lang.String)
    // */
    // @Override
    // public Intent getIntentForFavorites(Context context, long contactId, String contactName) {
    // Intent intent = new Intent(context, ChooseNumberActivity.class);
    //
    // intent.putExtra(ChooseNumberActivity.EXTRA_CONTACT_ID, contactId);
    // intent.putExtra(ChooseNumberActivity.EXTRA_CONTACT_NAME, contactName);
    //
    // return intent;
    // }

    /**
     * Gets a cursor to get raw contacts
     *
     * @return cursor to get raw contacts
     */
    public Cursor getAllContacts() {
//        if (shouldMimicReadingContact()) {
//            return SimulatedContactsProvider.getAllContacts();
//        }

        final Uri uri = ContactsContract.Contacts.CONTENT_URI;
        final String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor cursor = context.getContentResolver().query(uri, PROJECTION_CONTACTS_SUMMARY,
                SELECTION_VISIBLE_WITH_NUMBER, null, sortOrder);

//        if (cursor == null || cursor.getCount() == 0) {
//            Cursor tmpCursor = SimulatedContactsProvider.getAllContacts();
//            if (tmpCursor != null && tmpCursor.getCount() > 0) {
//                mShouldMimicContacts = true; // next time directly use the mimic one
//                if (cursor != null) {
//                    cursor.close();
//                }
//                cursor = tmpCursor; // replace cursor
//
//                    Log.i(TAG, "Starts using the mimic contacts reader. Contacts count: "
//                            + cursor.getCount());
//            }
//        }

        return cursor;
    }

    /**
     * Gets a cursor to get raw contacts which is marked as favorite in Android's native Contacts.
     *
     * @return cursor to get raw contacts
     */
    public Cursor getFavoriteContacts() {
//        if (shouldMimicReadingContact()) {
//            return SimulatedContactsProvider.getFavoriteContacts();
//        }
//
//        final Uri uri = ContactsContract.Contacts.CONTENT_URI;
//        final String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
//
//        Cursor cursor = context.getContentResolver().query(uri, PROJECTION_CONTACTS_SUMMARY,
//                SELECTION_FAVORITES_ANDV_ISIBLE_WITH_NUMBER_, null, sortOrder);
//
//        if (cursor == null || cursor.getCount() == 0) {
//            Cursor tmpCursor = SimulatedContactsProvider.getFavoriteContacts();
//            if (tmpCursor != null && tmpCursor.getCount() > 0) {
//                mShouldMimicContacts = true;
//                if (cursor != null) {
//                    cursor.close();
//                }
//                cursor = tmpCursor; // replace cursor
//            }
//        }

        return null;
    }

    @Override
    public Cursor getContact(long contactId) {

        final Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI,
                String.valueOf(contactId));
        final String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor cursor = context.getContentResolver().query(uri, PROJECTION_CONTACTS_SUMMARY,
                SELECTION_VISIBLE_WITH_NUMBER, null, sortOrder);


        return cursor;
    }



    @Override
    public int getContactIdFromNumber(String number) {
        int contactId = -1;
        String[] projection = new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.NUMBER,
                PhoneLookup._ID };
        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        Cursor c = context.getContentResolver().query(contactUri, projection, null, null, null);

        if (c.moveToFirst()) {
            contactId = (int) c.getLong(c.getColumnIndex(PhoneLookup._ID));
        }

        c.close();

        return contactId;
    }

    @Override
    public List<Long> getContactIdsFromNumber(String number) {
        List<Long> contactIds = new ArrayList<Long>();
        String[] projection = new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.NUMBER,
                PhoneLookup._ID };
        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        Cursor cursor = context.getContentResolver()
                .query(contactUri, projection, null, null, null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(PhoneLookup._ID);

            while (cursor.isAfterLast() == false) {
                long contactId = cursor.getLong(columnIndex);

                contactIds.add(contactId);
                cursor.moveToNext();
            }
        }

        cursor.close();

        return contactIds;
    }

    @Override
    public boolean isNumberInContact(String number, long contactId) {
        String[] projection = new String[] { PhoneLookup._ID };
        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        Cursor cursor = context.getContentResolver()
                .query(contactUri, projection, null, null, null);

        boolean ret = false;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(PhoneLookup._ID);

            while (cursor.isAfterLast() == false) {
                long id = cursor.getLong(columnIndex);
                if (id == contactId) {
                    ret = true;
                    break;
                }
            }
        }
        cursor.close();

        return ret;

    }

    @Override
    public String getContactNameFromNumber(String number) {

        // Which columns to return
        number = PhoneNumberUtils.stripSeparators(number);

        String[] projection = new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.NUMBER,
                PhoneLookup._ID };
        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        Cursor c = context.getContentResolver().query(contactUri, projection, null, null, null);
        String contactName = null;

        if (c.moveToFirst()) {
            contactName = c.getString(c.getColumnIndex(PhoneLookup.DISPLAY_NAME));
        }

        c.close();

        return contactName;
    }


    @Override
    public Cursor getContactNumbers(long contactId) {
        // String idString = String.valueOf(contactId);
        // ContentResolver cr = context.getContentResolver();
        /*
         * Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
         * ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?) GROUP BY (" +
         * ContactsContract.CommonDataKinds.Phone.NUMBER, new String[] { idString }, null);
         */

        Cursor distinctCursor = getDistinctContactsForId(contactId + "");

        return distinctCursor;
    }

    private Cursor getDistinctContactsForId(String contactId) {
        MatrixCursor distinctNumberCursor = null;
        Cursor phoneNumberCursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[] { contactId }, "");

        if (phoneNumberCursor != null && phoneNumberCursor.getCount() > 0
                && !phoneNumberCursor.isClosed()) {
            phoneNumberCursor.moveToFirst();

            Hashtable<String, ContactModel> sortedSet = new Hashtable<String, ContactModel>();

            do {
                int numberIndex = phoneNumberCursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = phoneNumberCursor.getString(numberIndex);

                int numberIdIndex = phoneNumberCursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
                String numberId = phoneNumberCursor.getString(numberIdIndex);

                ContactModel contactModel = new ContactModel();
                contactModel._id = numberId;
                contactModel.number = number;

                sortedSet.put(number, contactModel);
            } while (phoneNumberCursor.moveToNext());

            phoneNumberCursor.close();

            phoneNumberCursor = null;
            distinctNumberCursor = new MatrixCursor(new String[] { BaseColumns._ID, Phone.NUMBER });

            Set<String> keySet = sortedSet.keySet();

            Iterator<?> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();

                ContactModel contactModel = (ContactModel) sortedSet.get(key);

                String[] columnValues = new String[2];
                columnValues[0] = contactModel._id;
                columnValues[1] = contactModel.number;

                distinctNumberCursor.addRow(columnValues);
            }
        } else {
            if (phoneNumberCursor != null) {
                phoneNumberCursor.close();
            }
        }

        return distinctNumberCursor;
    }



    @Override
    public ContactIdAndName getContactIdAndNameFromNumber(String number) {
        ContactIdAndName entity = new ContactIdAndName();

        String[] projection = new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.NUMBER,
                PhoneLookup._ID };
        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        Cursor c = context.getContentResolver().query(contactUri, projection, null, null, null);

        if (c.moveToFirst()) {
            int contactId = (int) c.getLong(c.getColumnIndex(PhoneLookup._ID));
            String contactName = c.getString(c.getColumnIndex(PhoneLookup.DISPLAY_NAME));

            entity.id = contactId;
            entity.name = (!TextUtils.isEmpty(contactName)) ? contactName : number;
        } else {
            entity.id = -1;
            entity.name = number;
        }

        c.close();

        return entity;
    }

    @Override
    public Cursor getContactsByConstraint(String constraint) {
        String trim;
        if (constraint != null && !TextUtils.isEmpty(trim = constraint.trim())) {
           // else then normal case

            StringBuilder buffer = new StringBuilder();
            buffer.append("(");
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                buffer.append(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);
                buffer.append(" LIKE ?");
            } else {
                buffer.append(ContactsContract.Contacts.DISPLAY_NAME);
                buffer.append(" LIKE ?");
            }
            // buffer.append(" GLOB ?");
            buffer.append(") AND (");
            buffer.append(ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1)");
            String[] args = new String[] { "%" + trim + "%" };

            final Uri uri = ContactsContract.Contacts.CONTENT_URI;
            final Context context = GhoomoApplication.getAppContext();
            Cursor c = context.getContentResolver().query(uri,
                    ContactAccessorImpl.PROJECTION_CONTACTS_SUMMARY,
                    (buffer == null) ? null : buffer.toString(), args,
                    ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
            return c;
        } else {
            return getAllContacts();
        }
    }

    private boolean shouldMimicReadingContact() {
        return mShouldMimicContacts;
    }

}

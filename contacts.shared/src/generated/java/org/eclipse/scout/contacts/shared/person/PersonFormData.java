package org.eclipse.scout.contacts.shared.person;

import org.eclipse.scout.contacts.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.contacts.shared.common.AbstractNotesBoxData;
import org.eclipse.scout.contacts.shared.common.AbstractUrlImageFieldData;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import javax.annotation.Generated;
import java.util.Date;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@ClassId("3329750c-4428-4545-8ac5-6e85ae706f35-formdata")
@Generated(value = "org.eclipse.scout.contacts.client.person.PersonForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class PersonFormData extends AbstractFormData {
    private static final long serialVersionUID = 1L;

    public AddressBox getAddressBox() {
        return getFieldByClass(AddressBox.class);
    }

    public DateOfBirth getDateOfBirth() {
        return getFieldByClass(DateOfBirth.class);
    }

    public Email getEmail() {
        return getFieldByClass(Email.class);
    }

    public EmailWork getEmailWork() {
        return getFieldByClass(EmailWork.class);
    }

    public FirstName getFirstName() {
        return getFieldByClass(FirstName.class);
    }

    public GenderGroup getGenderGroup() {
        return getFieldByClass(GenderGroup.class);
    }

    public LastName getLastName() {
        return getFieldByClass(LastName.class);
    }

    public Mobile getMobile() {
        return getFieldByClass(Mobile.class);
    }

    public NotesBox getNotesBox() {
        return getFieldByClass(NotesBox.class);
    }

    public Organization getOrganization() {
        return getFieldByClass(Organization.class);
    }

    /**
     * access method for property PersonId.
     */
    public String getPersonId() {
        return getPersonIdProperty().getValue();
    }

    /**
     * access method for property PersonId.
     */
    public void setPersonId(String personId) {
        getPersonIdProperty().setValue(personId);
    }

    public PersonIdProperty getPersonIdProperty() {
        return getPropertyByClass(PersonIdProperty.class);
    }

    public Phone getPhone() {
        return getFieldByClass(Phone.class);
    }

    public PhoneWork getPhoneWork() {
        return getFieldByClass(PhoneWork.class);
    }

    public Picture getPicture() {
        return getFieldByClass(Picture.class);
    }

    public PictureUrl getPictureUrl() {
        return getFieldByClass(PictureUrl.class);
    }

    public Position getPosition() {
        return getFieldByClass(Position.class);
    }

    @ClassId("214592d2-ca84-446c-aab0-18d4db588385-formdata")
    public static class AddressBox extends AbstractAddressBoxData {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("8477a0b0-ab86-4dec-a7f1-9e1de7104834-formdata")
    public static class DateOfBirth extends AbstractValueFieldData<Date> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("20123362-21c8-4839-920c-8ab99fe4ddbd-formdata")
    public static class Email extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("42d30771-cb60-4cca-a008-883178f9d586-formdata")
    public static class EmailWork extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("6ed3eac5-ebe8-499e-928f-bd48e8ac6d1d-formdata")
    public static class FirstName extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("5583511e-64f1-4a26-a0ab-9e1064b5a711-formdata")
    public static class GenderGroup extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("bd6ca029-6b7d-42d9-b6ed-db5cd54cf2fd-formdata")
    public static class LastName extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("2b940466-ae27-4270-9e29-03f8bb57d8fd-formdata")
    public static class Mobile extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("34dba19e-c98f-4d39-ba97-99a868978412-formdata")
    public static class NotesBox extends AbstractNotesBoxData {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("70b3e56f-0b4b-4129-8f29-ca3d94224bd4-formdata")
    public static class Organization extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    public static class PersonIdProperty extends AbstractPropertyData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("9b81fb94-0e7c-477e-8ae0-95e5c694310c-formdata")
    public static class Phone extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("53fa1025-2607-47ca-9321-77d54db207d7-formdata")
    public static class PhoneWork extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("6366a23e-f8ba-4b50-b814-202e63daffc8-formdata")
    public static class Picture extends AbstractUrlImageFieldData {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("617ffd40-0d69-4d02-b4f8-90c28c68c6ce-formdata")
    public static class PictureUrl extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("41cc63ca-11b0-4a8d-b049-91d5dcc3b185-formdata")
    public static class Position extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }
}

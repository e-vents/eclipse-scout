package org.eclipse.scout.contacts.shared.organization;

import org.eclipse.scout.contacts.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.contacts.shared.common.AbstractNotesBoxData;
import org.eclipse.scout.contacts.shared.common.AbstractUrlImageFieldData;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import javax.annotation.Generated;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@ClassId("25d2a192-70fb-4468-bfca-4bd516a73366-formdata")
@Generated(value = "org.eclipse.scout.contacts.client.organization.OrganizationForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class OrganizationFormData extends AbstractFormData {
    private static final long serialVersionUID = 1L;

    public AddressBox getAddressBox() {
        return getFieldByClass(AddressBox.class);
    }

    public Email getEmail() {
        return getFieldByClass(Email.class);
    }

    public Homepage getHomepage() {
        return getFieldByClass(Homepage.class);
    }

    public Name getName() {
        return getFieldByClass(Name.class);
    }

    public NotesBox getNotesBox() {
        return getFieldByClass(NotesBox.class);
    }

    /**
     * access method for property OrganizationId.
     */
    public String getOrganizationId() {
        return getOrganizationIdProperty().getValue();
    }

    /**
     * access method for property OrganizationId.
     */
    public void setOrganizationId(String organizationId) {
        getOrganizationIdProperty().setValue(organizationId);
    }

    public OrganizationIdProperty getOrganizationIdProperty() {
        return getPropertyByClass(OrganizationIdProperty.class);
    }

    public Phone getPhone() {
        return getFieldByClass(Phone.class);
    }

    public Picture getPicture() {
        return getFieldByClass(Picture.class);
    }

    @ClassId("2a10bd00-de56-4a97-a5b2-6a8a0aae925f-formdata")
    public static class AddressBox extends AbstractAddressBoxData {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("0b4d059d-ec81-4e93-9a99-2512d734ebac-formdata")
    public static class Email extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("68008603-257f-45dc-b8ea-d1e066682205-formdata")
    public static class Homepage extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("4c1a0dea-6c04-4cad-b26b-8d5cc1b786a9-formdata")
    public static class Name extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("85f4dfb0-f375-4e90-be92-b59e9bc2ebcf-formdata")
    public static class NotesBox extends AbstractNotesBoxData {
        private static final long serialVersionUID = 1L;
    }

    public static class OrganizationIdProperty extends AbstractPropertyData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("504a4845-d307-4238-a2e9-9e785c1477ac-formdata")
    public static class Phone extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("d80625e3-b548-47e4-9cae-42d70aaa568f-formdata")
    public static class Picture extends AbstractUrlImageFieldData {
        private static final long serialVersionUID = 1L;
    }
}

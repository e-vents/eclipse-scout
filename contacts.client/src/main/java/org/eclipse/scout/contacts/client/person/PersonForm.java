package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.client.common.*;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.CancelButton;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.OkButton;
import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.contacts.shared.organization.OrganizationLookupCall;
import org.eclipse.scout.contacts.shared.person.*;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;

import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.EmailField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.MobileField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.PhoneField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.NotesBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.EmailWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.OrganizationField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PhoneWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PositionField;

import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.PictureUrlField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.DateOfBirthField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.FirstNameField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.GenderGroup;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.LastNameField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.PictureField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.platform.util.collection.OrderedCollection;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@ClassId("3329750c-4428-4545-8ac5-6e85ae706f35")
// links the form with its form data class
@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

    // represents the person's primary key
    private String personId;

    @Override
    protected boolean execValidate() {
        boolean noFirstName = StringUtility.isNullOrEmpty(getFirstNameField().getValue());
        boolean noLastName = StringUtility.isNullOrEmpty(getLastNameField().getValue());

        if (noFirstName && noLastName) {
            getFirstNameField().requestFocus();

            throw new VetoException(TEXTS.get("MissingName"));
        }
        return true;
    }

    @FormData // defines field as a property that will be included in the form data
    public String getPersonId() {
        return personId;
    }

    @FormData // defines field as a property that will be included in the form data
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /*
    The object returned by this method is used by the framework to verify
    if a specific entity is already opened in some other form.
     */
    @Override
    public Object computeExclusiveKey() {
        return getPersonId();
    }

    /*
    Configure this form to be opened in the view mode.
    Views are opened in the bench area of the user interface.
     */
    @Override
    protected int getConfiguredDisplayHint() {
        return IForm.DISPLAY_HINT_VIEW;
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("Person");
    }

    /*
    every Scout form has a MainBox. It contains all visible UI components
     */
    @Order(1000)
    @ClassId("c1d2d806-5fad-48a9-9095-3f4af7574191")
    public class MainBox extends AbstractGroupBox {

        @Override
        protected void injectMenusInternal(OrderedCollection<IMenu> menus) {
            BEANS.get(ContactsHelper.class).injectReadOnlyMenu(menus);
        }

        // upper part (green)
        @Order(0)
        @ClassId("0e53a517-edc9-4d27-9969-10e765ce4f6c")
        public class GeneralBox extends AbstractGroupBox {

            @Override
            protected String getConfiguredLabel() {
                return TEXTS.get("General");
            }

            @Override
            protected double getConfiguredGridWeightY() {
                // do not allow the general box to grow or shrink vertically.
                return 0;
            }

            @Override
            protected boolean getConfiguredLabelVisible() {
                return false;
            }

            @Order(10)
            @ClassId("617ffd40-0d69-4d02-b4f8-90c28c68c6ce")
            public class PictureUrlField extends AbstractStringField {

                @Override
                protected boolean getConfiguredVisible() {
                    return false;
                }
            }

            // extending the self-created template fields class
            @Order(20)
            @ClassId("6366a23e-f8ba-4b50-b814-202e63daffc8")
            public class PictureField extends AbstractUrlImageField {

                @Override
                protected Class<PictureUrlField> getConfiguredMasterField() {
                    return PictureUrlField.class;
                }

                @Override
                protected void execChangedMasterValue(Object newMasterValue) {
                    updateImage((String) newMasterValue);
                }

                @Override
                protected boolean getConfiguredLabelVisible() {
                    return false;
                }

                @Override
                protected int getConfiguredGridH() {
                    return 5;
                }

                @Override
                protected boolean getConfiguredAutoFit() {
                    return true;
                }

                @Override
                protected String getConfiguredImageId() {
                    return Icons.User;
                }

                protected void updateImage(String url) {
                    setImageUrl(url);
                }
            }

            @Order(250)
            @ClassId("6ed3eac5-ebe8-499e-928f-bd48e8ac6d1d")
            public class FirstNameField extends AbstractStringField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("FirstName");
                }
            }

            @Order(375)
            @ClassId("bd6ca029-6b7d-42d9-b6ed-db5cd54cf2fd")
            public class LastNameField extends AbstractStringField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("LastName");
                }
            }

            @Order(437)
            @ClassId("8477a0b0-ab86-4dec-a7f1-9e1de7104834")
            public class DateOfBirthField extends AbstractDateField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("DateOfBirth");
                }
            }

            @Order(468)
            @ClassId("5583511e-64f1-4a26-a0ab-9e1064b5a711")
            public class GenderGroup extends AbstractRadioButtonGroup<String> {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Gender");
                }

                // the code from GenderCodeType will be used to determine the actual radio buttons
                @Override
                protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
                    return GenderCodeType.class;
                }
            }
        }

        // lower part (blue)
        @Order(500)
        @ClassId("2ec2376e-890b-4014-ac23-687a78b78b25")
        public class DetailsBox extends AbstractTabBox {

            /*
            at this level, the three tabs are arranged
             */
            @Order(1000)
            @ClassId("e432d618-4ef9-410f-91ae-4b51dc935c87")
            public class ContactInfoBox extends AbstractGroupBox {

                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("ContactInfo");
                }

                @Order(1000)
                @ClassId("214592d2-ca84-446c-aab0-18d4db588385")
                public class AddressBox extends AbstractAddressBox { }

                @Order(5000)
                @ClassId("9b81fb94-0e7c-477e-8ae0-95e5c694310c")
                public class PhoneField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Phone");
                    }
                }

                @Order(6000)
                @ClassId("2b940466-ae27-4270-9e29-03f8bb57d8fd")
                public class MobileField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Mobile");
                    }
                }

                @Order(7000)
                @ClassId("20123362-21c8-4839-920c-8ab99fe4ddbd")
                public class EmailField extends AbstractEmailField {

                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Email");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 64;
                    }
                }
            }

            @Order(2000)
            @ClassId("e6394a9f-25ae-4d1a-b1af-e66aec7c5b59")
            public class WorkBox extends AbstractGroupBox {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Work");
                }

                @Order(0)
                @ClassId("70b3e56f-0b4b-4129-8f29-ca3d94224bd4")
                public class OrganizationField extends AbstractSmartField<String> {

                    @Override
                    protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                        return OrganizationLookupCall.class;
                    }
                    
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Organization");
                    }
                }

                @Order(1000)
                @ClassId("41cc63ca-11b0-4a8d-b049-91d5dcc3b185")
                public class PositionField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Position");
                    }
                }

                @Order(3000)
                @ClassId("53fa1025-2607-47ca-9321-77d54db207d7")
                public class PhoneWorkField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Phone");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }

                @Order(4000)
                @ClassId("42d30771-cb60-4cca-a008-883178f9d586")
                public class EmailWorkField extends AbstractStringField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("Email");
                    }

                    @Override
                    protected int getConfiguredMaxLength() {
                        return 128;
                    }
                }
            }

            @Order(3000)
            @ClassId("34dba19e-c98f-4d39-ba97-99a868978412")
            public class NotesBox extends AbstractNotesBox { }
        }

        @Order(2000)
        @ClassId("fe77520d-e94c-46c4-af47-325433332619")
        public class OkButton extends AbstractOkButton { }

        @Order(3000)
        @ClassId("bb9e8426-cde1-4f9e-85a4-e795362c96b0")
        public class CancelButton extends AbstractCancelButton { }
    }

    public void startModify() {
        startInternalExclusive(new ModifyHandler());
    }

    public void startNew() {
        startInternal(new NewHandler());
    }

    public class NewHandler extends AbstractFormHandler {

        @Override
        protected void execStore() {
            IPersonService service = BEANS.get(IPersonService.class);
            PersonFormData formData = new PersonFormData();
            exportFormData(formData);
            formData = service.create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        /*
        In these methods (execLoad & execStore) the form fetches data from the
        Scout backend application and/or sends new data to the backend server.
        */
        @Override
        protected void execLoad() {
            IPersonService service = BEANS.get(IPersonService.class);
            PersonFormData formData = new PersonFormData();
            exportFormData(formData);
            formData = service.load(formData);
            importFormData(formData);

            getForm().setSubTitle(calculateSubTitle());
        }

        @Override
        protected void execStore() {
            IPersonService service = BEANS.get(IPersonService.class);
            PersonFormData formData = new PersonFormData();
            exportFormData(formData);
            service.store(formData);
        }
    }

    protected String calculateSubTitle() {
        return StringUtility.join(" ",
            getFirstNameField().getValue(),
            getLastNameField().getValue());
    }

    public AddressBox getAddressBox() {
        return getFieldByClass(AddressBox.class);
    }

    public MainBox getMainBox() {
        return getFieldByClass(MainBox.class);
    }

    public OkButton getOkButton() {
        return getFieldByClass(OkButton.class);
    }

    public CancelButton getCancelButton() {
        return getFieldByClass(CancelButton.class);
    }

    public ContactInfoBox getContactInfoBox() {
        return getFieldByClass(ContactInfoBox.class);
    }

    public DateOfBirthField getDateOfBirthField() {
        return getFieldByClass(DateOfBirthField.class);
    }

    public DetailsBox getDetailsBox() {
        return getFieldByClass(DetailsBox.class);
    }

    public EmailWorkField getEmailWorkField() {
        return getFieldByClass(EmailWorkField.class);
    }

    public FirstNameField getFirstNameField() {
        return getFieldByClass(FirstNameField.class);
    }

    public GenderGroup getGenderGroup() {
        return getFieldByClass(GenderGroup.class);
    }

    public GeneralBox getGeneralBox() {
        return getFieldByClass(GeneralBox.class);
    }

    public LastNameField getLastNameField() {
        return getFieldByClass(LastNameField.class);
    }

    public MobileField getMobileField() {
        return getFieldByClass(MobileField.class);
    }

    public EmailField getEmailField() {
        return getFieldByClass(EmailField.class);
    }

    public PhoneField getPhoneField() {
        return getFieldByClass(PhoneField.class);
    }

    public NotesBox getNotesBox() {
        return getFieldByClass(NotesBox.class);
    }

    public OrganizationField getOrganizationField() {
        return getFieldByClass(OrganizationField.class);
    }

    public PhoneWorkField getPhoneWorkField() {
        return getFieldByClass(PhoneWorkField.class);
    }

    public WorkBox getWorkBox() {
        return getFieldByClass(WorkBox.class);
    }

    public PictureField getPictureField() {
        return getFieldByClass(PictureField.class);
    }

    public PositionField getPositionField() {
        return getFieldByClass(PositionField.class);
    }

    public PictureUrlField getPictureUrlField() {
        return getFieldByClass(PictureUrlField.class);
    }
}

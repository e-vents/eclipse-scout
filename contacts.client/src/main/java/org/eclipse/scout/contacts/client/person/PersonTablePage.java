package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.util.Set;

@Data(PersonTablePageData.class)
@ClassId("3d57b435-e01d-4df3-be34-ab9c32710576")
public class PersonTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IPersonService.class).getPersonTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("Persons");
    }

    @ClassId("c4d311d3-0e12-4c35-909f-8559442e3cb0")
    public class Table extends AbstractTable {

        /*
        This action gets executed when the user presses Enter on a table row or double clicks on a table row.
         */
        @Override
        protected Class<? extends IMenu> getConfiguredDefaultMenu() {
            return EditMenu.class;
        }

        @Order(1000)
        @ClassId("676ca69b-6614-4813-afec-901cf5234ae7")
        public class EditMenu extends AbstractMenu {
            @Override
            protected String getConfiguredText() {
                return TEXTS.get("Edit");
            }

            /*
            @Override
            protected Set<? extends IMenuType> getConfiguredMenuTypes() {
                return org.eclipse.scout.rt.platform.util.CollectionUtility.hashSet(org.eclipse.scout.rt.client.ui.action.menu.TableMenuType.SingleSelection, org.eclipse.scout.rt.client.ui.action.menu.TableMenuType.MultiSelection);
            }
             */

            @Override
            protected void execAction() {
                // transfers the primary key of the selected person row to the person form
                PersonForm form = new PersonForm();
                form.setPersonId(getPersonIdColumn().getSelectedValue());
                form.addFormListener(new PersonFormListener());
                // start the form using it new handler
                form.startModify();
            }
        }

        @Order(2000)
        @ClassId("0e442161-dc17-4389-a97a-b5f7e470a610")
        public class NewMenu extends AbstractMenu {
            @Override
            protected String getConfiguredText() {
                return TEXTS.get("New");
            }

            @Override
            protected Set<? extends IMenuType> getConfiguredMenuTypes() {
                // Including TableMenuType.EmptySpace in the return value activates the "New" menu even when no row is selected.
                return CollectionUtility.<IMenuType> hashSet(TableMenuType.EmptySpace, TableMenuType.SingleSelection);
            }

            @Override
            protected void execAction() {
                PersonForm form = new PersonForm();
                form.addFormListener(new PersonFormListener());

                form.startNew();
            }
        }

        private class PersonFormListener implements FormListener {

            @Override
            public void formChanged(FormEvent e) {
                // reload page to reflect new/changed data after saving any changes
                if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
                    reloadPage();
                }
            }
        }

        // automatically generated getters
        public CityColumn getCityColumn() {
            return getColumnSet().getColumnByClass(CityColumn.class);
        }

        public CountryColumn getCountryColumn() {
            return getColumnSet().getColumnByClass(CountryColumn.class);
        }

        public EmailColumn getEmailColumn() {
            return getColumnSet().getColumnByClass(EmailColumn.class);
        }

        public FirstNameColumn getFirstNameColumn() {
            return getColumnSet().getColumnByClass(FirstNameColumn.class);
        }

        public LastNameColumn getLastNameColumn() {
            return getColumnSet().getColumnByClass(LastNameColumn.class);
        }

        public MobileColumn getMobileColumn() {
            return getColumnSet().getColumnByClass(MobileColumn.class);
        }

        public OrganizationColumn getOrganizationColumn() {
            return getColumnSet().getColumnByClass(OrganizationColumn.class);
        }

        // from here on generated according to the tutorial
        public PersonIdColumn getPersonIdColumn() {
            return getColumnSet().getColumnByClass(PersonIdColumn.class);
        }

        public PhoneColumn getPhoneColumn() {
            return getColumnSet().getColumnByClass(PhoneColumn.class);
        }

        @Order(1000)
        @ClassId("d80e3f33-93f0-44ef-a34a-647dd5a3aff9")
        public class PersonIdColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("MyNlsKey");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }

            // returning false makes this column invisible
            @Override
            protected boolean getConfiguredDisplayable() {
                return false;
            }

            // marks this attribute as primary key
            @Override
            protected boolean getConfiguredPrimaryKey() {
                return true;
            }
        }

        @Order(2000)
        @ClassId("187f8495-9c58-407d-9bc3-003cdec7bb0d")
        public class FirstNameColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("FirstName");
            }

            @Override
            protected int getConfiguredWidth() {
                return 120;
            }
        }

        @Order(3000)
        @ClassId("e596784a-34c6-4967-ba25-f2505a39992c")
        public class LastNameColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("LastName");
            }

            @Override
            protected int getConfiguredWidth() {
                return 120;
            }
        }

        @Order(4000)
        @ClassId("a15977fc-7875-41dd-a975-4ef9eff1f60c")
        public class CityColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("City");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(5000)
        @ClassId("cec61b5a-c55c-4aa2-b225-baa5af6982ec")
        public class CountryColumn extends AbstractSmartColumn<String> {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Country");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }

            // this lookup call is used to map country code to the country names used in the UI
            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                return CountryLookupCall.class;
            }
        }

        @Order(6000)
        @ClassId("5a0034d1-10b9-491e-9925-66eb68e73205")
        public class PhoneColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Phone");
            }

            @Override
            protected boolean getConfiguredDisplayable() {
                return false;
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(7000)
        @ClassId("eca820f8-ae41-4bad-ab29-437e0cc4b63d")
        public class MobileColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Mobile");
            }

            @Override
            protected boolean getConfiguredDisplayable() {
                return false;
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(8000)
        @ClassId("78ba8771-e25c-41c9-9e8a-cb28d0d81d30")
        public class EmailColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Email");
            }

            @Override
            protected boolean getConfiguredDisplayable() {
                return false;
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(9000)
        @ClassId("4eb95b89-a409-457d-9269-12eeaaa013cd")
        public class OrganizationColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Organization");
            }

            @Override
            protected boolean getConfiguredDisplayable() {
                return false;
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }
    }
}

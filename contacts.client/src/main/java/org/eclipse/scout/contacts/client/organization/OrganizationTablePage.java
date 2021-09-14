package org.eclipse.scout.contacts.client.organization;

import org.eclipse.scout.contacts.client.organization.OrganizationTablePage.Table;
import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.contacts.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.Set;

@Data(OrganizationTablePageData.class)
@ClassId("9a4830e3-c5c7-47bc-b689-8318f4181866")
public class OrganizationTablePage extends AbstractPageWithTable<Table> {

    @Override
    protected IPage<?> execCreateChildPage(ITableRow row) {
        OrganizationNodePage childPage = new OrganizationNodePage();
        childPage.setOrganizationId(getTable().getOrganizationIdColumn().getValue(row));
        return childPage;
    }

    // loading of the data from the server, returns a OrganizationTablePageData object
    @Override
    protected void execLoadData(SearchFilter filter) {
      // importPageData() transfers the data from the page data into the UI
      importPageData(BEANS.get(IOrganizationService.class) // returns a reference to a client proxy service
        .getOrganizationTableData(filter)); // is executed on the corresponding server service
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("Organizations");
    }

    @Override
    protected String getConfiguredOverviewIconId() {
        return Icons.Star;
    }

    @ClassId("af0a3771-e675-403e-bc57-be9c82063b64")
    public class Table extends AbstractTable {

        @Override
        protected Class<? extends IMenu> getConfiguredDefaultMenu() {
            return OrganizationTablePage.Table.EditMenu.class;
        }

        @Order(1000)
        @ClassId("2ce3d286-04ec-4eb2-92bb-fec924186cf1")
        public class EditMenu extends AbstractMenu {

            @Override
            protected String getConfiguredText() {
                return TEXTS.get("Edit");
            }

            @Override
            protected void execAction() {
                final OrganizationForm form = new OrganizationForm();
                form.setOrganizationId(getOrganizationIdColumn().getSelectedValue());
                form.addFormListener(e -> {
                    if (FormEvent.TYPE_CLOSED == e.getType() && form.isFormStored()) {
                        reloadPage();
                    }
                });

                form.startModify();
            }
        }

        @Order(2000)
        @ClassId("ae6303f0-0041-4545-a49b-dc7f6fd23666")
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
                final OrganizationForm form = new OrganizationForm();
                form.addFormListener(e -> {
                    if (FormEvent.TYPE_CLOSED == e.getType() && form.isFormStored()) {
                        reloadPage();
                    }
                });
                form.startNew();
            }
        }

        @Order(0)
        @ClassId("13f79cd8-f8c4-4a85-9535-1b117f486f54")
        public class OrganizationIdColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("OrganizationID");
            }

            @Override
            protected boolean getConfiguredDisplayable() {
                return false;
            }

            @Override
            protected boolean getConfiguredPrimaryKey() {
                return true;
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(1000)
        @ClassId("3ebef094-de91-4d96-b6f9-f48809a8e609")
        public class NameColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Name");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(2000)
        @ClassId("fdc7f074-4cfc-4cf6-a855-fd4eb9bba270")
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

        @Order(3000)
        @ClassId("6502812c-a875-4520-862b-fa28b718b98d")
        public class CountryColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Country");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(4000)
        @ClassId("a1096ce9-f445-4a91-85e5-95e6d897f481")
        public class HomepageColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Homepage");
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

        public CityColumn getCityColumn() {
            return getColumnSet().getColumnByClass(CityColumn.class);
        }

        public CountryColumn getCountryColumn() {
            return getColumnSet().getColumnByClass(CountryColumn.class);
        }

        public HomepageColumn getHomepageColumn() {
            return getColumnSet().getColumnByClass(HomepageColumn.class);
        }

        public NameColumn getNameColumn() {
            return getColumnSet().getColumnByClass(NameColumn.class);
        }

        public OrganizationIdColumn getOrganizationIdColumn() {
            return getColumnSet().getColumnByClass(OrganizationIdColumn.class);
        }
    }
}

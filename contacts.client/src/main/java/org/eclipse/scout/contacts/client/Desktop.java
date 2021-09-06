package org.eclipse.scout.contacts.client;

import org.eclipse.scout.contacts.client.contact.ContactOutline;
import org.eclipse.scout.contacts.client.organization.OrganizationForm;
import org.eclipse.scout.contacts.client.person.PersonForm;
import org.eclipse.scout.contacts.client.search.SearchOutline;
import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;

import java.util.List;

/**
 * @author pha
 */
public class Desktop extends AbstractDesktop {

    public Desktop() { }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("ApplicationTitle");
    }

    @Override
    protected String getConfiguredLogoId() {
        return Icons.AppLogo;
    }

    @Override
    protected List<Class<? extends IOutline>> getConfiguredOutlines() {
        return CollectionUtility.<Class<? extends IOutline>>arrayList(ContactOutline.class, SearchOutline.class);
    }

    @Override
    protected void execDefaultView() {
        selectFirstVisibleOutline();
    }

    protected void selectFirstVisibleOutline() {
        for (IOutline outline : getAvailableOutlines()) {
            if (outline.isEnabled() && outline.isVisible()) {
                setOutline(outline.getClass());
                return;
            }
        }
    }

    @Order(1000)
    public class ContactOutlineViewButton extends AbstractOutlineViewButton {

        public ContactOutlineViewButton() {
            this(ContactOutline.class);
        }

        protected ContactOutlineViewButton(Class<? extends ContactOutline> outlineClass) {
            super(Desktop.this, outlineClass);
        }

        @Override
        protected String getConfiguredKeyStroke() {
            return IKeyStroke.F2;
        }
    }

    @Order(2000)
    public class SearchOutlineViewButton extends AbstractOutlineViewButton {

        public SearchOutlineViewButton() {
            this(SearchOutline.class);
        }

        protected SearchOutlineViewButton(Class<? extends SearchOutline> outlineClass) {
            super(Desktop.this, outlineClass);
        }

        @Override
        protected DisplayStyle getConfiguredDisplayStyle() {
            return DisplayStyle.TAB;
        }

        @Override
        protected String getConfiguredKeyStroke() {
            return IKeyStroke.F3;
        }
    }

    // following three methods: pha
    @Order(1000)
    @ClassId("16a5c2c1-a85b-4f28-b6d4-b6db069160d8")
    public class QuickAccessMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
            return TEXTS.get("QuickAccess");
        }

        @Override
        protected void execAction() {
        }

        @Order(1000)
        @ClassId("88cd82f3-8734-436d-9c7a-443cba8aa47f")
        public class NewPersonMenu extends AbstractMenu {
            @Override
            protected String getConfiguredText() {
                return TEXTS.get("NewPersonMenu");
            }

            @Override
            protected void execAction() {
                new PersonForm().startNew();
            }
        }

        @Order(2000)
        @ClassId("bfcb1f1b-8d71-490e-a249-b8ce0d884634")
        public class NewOrganizationMenu extends AbstractMenu {
            @Override
            protected String getConfiguredText() {
                return TEXTS.get("NewOrganizationMenu");
            }

            @Override
            protected void execAction() {
                new OrganizationForm().startNew();
            }
        }
    }

    @Order(2000)
    @ClassId("79085a54-142a-4e2a-b9aa-4ea56c128d93")
    public class OptionsMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
            return TEXTS.get("Options");
        }

        @Override
        protected void execAction() {

        }
    }

    @Order(3000)
    @ClassId("07e20982-fadd-4fa9-b5ca-d67c9721a30f")
    public class UserMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
            return TEXTS.get("User");
        }

        @Override
        protected void execAction() {

        }
    }
}

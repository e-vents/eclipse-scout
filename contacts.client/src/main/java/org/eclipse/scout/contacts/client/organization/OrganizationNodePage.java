package org.eclipse.scout.contacts.client.organization;

import org.eclipse.scout.contacts.client.person.PersonTablePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;

import java.util.List;

public class OrganizationNodePage extends AbstractPageWithNodes {

    private String organizationId;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    protected void execCreateChildPages(List<IPage<?>> pageList) {
        PersonTablePage personTablePage = new PersonTablePage();
        personTablePage.setOrganizationId(getOrganizationId());
        pageList.add(personTablePage);
    }
}

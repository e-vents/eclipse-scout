package org.eclipse.scout.contacts.server.organization;

import org.eclipse.scout.contacts.server.sql.SQLs;
import org.eclipse.scout.contacts.shared.organization.IOrganizationLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;

public class OrganizationLookupService
    extends AbstractSqlLookupService<String>
    implements IOrganizationLookupService {

    @Override
    protected String getConfiguredSqlSelect() {
        return SQLs.ORGANIZATION_LOOKUP;
    }
}

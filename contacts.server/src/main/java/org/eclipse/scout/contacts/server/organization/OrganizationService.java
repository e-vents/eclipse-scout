package org.eclipse.scout.contacts.server.organization;

import org.eclipse.scout.contacts.server.sql.SQLs;
import org.eclipse.scout.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.contacts.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class OrganizationService implements IOrganizationService {

  /*
  access the database and map the data into a pageData object
   */
  @Override
  public OrganizationTablePageData getOrganizationTableData(SearchFilter filter) {
    OrganizationTablePageData pageData = new OrganizationTablePageData();
    // added following the tutorial #sec-contacts_jdbc_fetching_data
    // assembling the sql statement
    String sql = SQLs.ORGANIZATION_PAGE_SELECT + SQLs.ORGANIZATION_PAGE_DATA_SELECT_INTO;
    // fetching the data from the database and storing the result in pageData
    SQL.selectInto(sql, new NVPair("page", pageData));
    return pageData;
  }
}

package org.eclipse.scout.contacts.server.person;

import org.eclipse.scout.contacts.server.sql.SQLs;
import org.eclipse.scout.contacts.shared.person.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.UUID;

public class PersonService implements IPersonService {
    @Override
    public PersonTablePageData getPersonTableData(SearchFilter filter) {

        PersonTablePageData pageData = new PersonTablePageData();

        // added following the tutorial #sec-contacts_jdbc_fetching_data
        String sql = SQLs.PERSON_PAGE_SELECT + SQLs.PERSON_PAGE_DATA_SELECT_INTO;
        SQL.selectInto(sql, new NVPair("page", pageData));
        return pageData;
    }

    @Override
    public PersonFormData prepareCreate(PersonFormData formData) {
        if (!ACCESS.check(new CreatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [pha] add business logic here.
        return formData;
    }

    @Override
    public PersonFormData create(PersonFormData formData) {
        if (!ACCESS.check(new CreatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }

        // add a unique person id if necessary
        if (StringUtility.isNullOrEmpty(formData.getPersonId())) {
            formData.setPersonId(UUID.randomUUID().toString());
        }

        /*
        The SQL insert statement adds a new person entry in the database.
        Only the primary key is used to create this entry
         */
        SQL.insert(SQLs.PERSON_INSERT, formData);

        // To save all other person attributes provided in the form data, the store method is reused
        return store(formData);
    }

    @Override
    public PersonFormData load(PersonFormData formData) {
        if (!ACCESS.check(new ReadPersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }

        // The SQL select into transfers the person data from the database into the form data
        SQL.selectInto(SQLs.PERSON_SELECT, formData);

        return formData;
    }

    @Override
    public PersonFormData store(PersonFormData formData) {
        if (!ACCESS.check(new UpdatePersonPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }

        // The SQL update statement transfers all person attributes provided in the form data to the person table
        SQL.update(SQLs.PERSON_UPDATE, formData);

        return formData;
    }
}

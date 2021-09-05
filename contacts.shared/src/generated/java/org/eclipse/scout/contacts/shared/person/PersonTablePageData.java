package org.eclipse.scout.contacts.shared.person;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@ClassId("3d57b435-e01d-4df3-be34-ab9c32710576-formdata")
@Generated(value = "org.eclipse.scout.contacts.client.person.PersonTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class PersonTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public PersonTableRowData addRow() {
        return (PersonTableRowData) super.addRow();
    }

    @Override
    public PersonTableRowData addRow(int rowState) {
        return (PersonTableRowData) super.addRow(rowState);
    }

    @Override
    public PersonTableRowData createRow() {
        return new PersonTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return PersonTableRowData.class;
    }

    @Override
    public PersonTableRowData[] getRows() {
        return (PersonTableRowData[]) super.getRows();
    }

    @Override
    public PersonTableRowData rowAt(int index) {
        return (PersonTableRowData) super.rowAt(index);
    }

    public void setRows(PersonTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class PersonTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
        public static final String personId = "personId";
        public static final String firstName = "firstName";
        public static final String lastName = "lastName";
        public static final String city = "city";
        public static final String country = "country";
        public static final String phone = "phone";
        public static final String mobile = "mobile";
        public static final String email = "email";
        public static final String organization = "organization";
        private String m_personId;
        private String m_firstName;
        private String m_lastName;
        private String m_city;
        private String m_country;
        private String m_phone;
        private String m_mobile;
        private String m_email;
        private String m_organization;

        public String getPersonId() {
            return m_personId;
        }

        public void setPersonId(String newPersonId) {
            m_personId = newPersonId;
        }

        public String getFirstName() {
            return m_firstName;
        }

        public void setFirstName(String newFirstName) {
            m_firstName = newFirstName;
        }

        public String getLastName() {
            return m_lastName;
        }

        public void setLastName(String newLastName) {
            m_lastName = newLastName;
        }

        public String getCity() {
            return m_city;
        }

        public void setCity(String newCity) {
            m_city = newCity;
        }

        public String getCountry() {
            return m_country;
        }

        public void setCountry(String newCountry) {
            m_country = newCountry;
        }

        public String getPhone() {
            return m_phone;
        }

        public void setPhone(String newPhone) {
            m_phone = newPhone;
        }

        public String getMobile() {
            return m_mobile;
        }

        public void setMobile(String newMobile) {
            m_mobile = newMobile;
        }

        public String getEmail() {
            return m_email;
        }

        public void setEmail(String newEmail) {
            m_email = newEmail;
        }

        public String getOrganization() {
            return m_organization;
        }

        public void setOrganization(String newOrganization) {
            m_organization = newOrganization;
        }
    }
}

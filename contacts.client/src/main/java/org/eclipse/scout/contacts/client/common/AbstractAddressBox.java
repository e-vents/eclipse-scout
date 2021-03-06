package org.eclipse.scout.contacts.client.common;

import org.eclipse.scout.contacts.client.common.AbstractAddressBox.LocationBox.CityField;
import org.eclipse.scout.contacts.client.common.AbstractAddressBox.LocationBox.CountryField;
import org.eclipse.scout.contacts.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.DefaultSubtypeSdkCommand;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@ClassId("d03c9495-530b-4bad-8b05-414ff7edce7a")
@FormData(value = AbstractAddressBoxData.class,
    sdkCommand = SdkCommand.CREATE,
    defaultSubtypeSdkCommand = DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractAddressBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredBorderVisible() {
        return false;
    }

    @Override
    protected int getConfiguredGridColumnCount() {
        return 1;
    }

    @Override
    protected int getConfiguredGridH() {
        return 3;
    }

    @Override
    protected int getConfiguredGridW() {
        return 1;
    }

    public StreetField getStreetField() {
        return getFieldByClass(StreetField.class);
    }

    public LocationBox getLocationBox() {
        return getFieldByClass(LocationBox.class);
    }

    public CityField getCityField() {
        return getFieldByClass(CityField.class);
    }

    public CountryField getCountryField() {
        return getFieldByClass(CountryField.class);
    }

    @Order(1000)
    @ClassId("87206b83-a4e4-4bb8-a58e-2596b6dbedd3")
    public class StreetField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
            return TEXTS.get("Street");
        }

        @Override
        protected void execChangedValue() {
            verifyAllFields();
        }
    }

    @Order(2000)
    @ClassId("b45a1137-1043-443a-8a9f-9ad178b0fc3c")
    public class LocationBox extends AbstractSequenceBox {

        @Override
        protected boolean getConfiguredAutoCheckFromTo() {
            return false;
        }

        @Override
        protected String getConfiguredLabel() {
            return TEXTS.get("Location");
        }

        @Order(1000)
        @ClassId("e6c16bd4-2e66-4ec1-bb8d-a35d95bd7c6d")
        public class CityField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
                return TEXTS.get("City");
            }

            @Override
            protected byte getConfiguredLabelPosition() {
                return LABEL_POSITION_ON_FIELD;
            }

            @Override
            protected void execChangedValue() {
                verifyAllFields();
            }
        }

        @Order(2000)
        @ClassId("27a07cc8-0e9a-483b-96e1-c6e8c292525d")
        public class CountryField extends AbstractSmartField<String> {

            @Override
            protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
                return CountryLookupCall.class;
            }

            @Override
            protected String getConfiguredLabel() {
                return TEXTS.get("Country");
            }

            @Override
            protected void execChangedValue() {
                verifyAllFields();
            }

            @Override
            protected byte getConfiguredLabelPosition() {
                return LABEL_POSITION_ON_FIELD;
            }
        }
    }

    protected void verifyAllFields() {
        boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
        boolean hasCity = StringUtility.hasText(getCityField().getValue());

        getCityField().setMandatory(hasStreet);
        getCountryField().setMandatory(hasStreet || hasCity);
    }
}

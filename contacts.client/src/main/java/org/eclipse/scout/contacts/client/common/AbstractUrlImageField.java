package org.eclipse.scout.contacts.client.common;

import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.contacts.shared.common.AbstractUrlImageFieldData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ImageFieldMenuType;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;

import java.util.Set;

@ClassId("73a4276f-77b2-4ad2-b414-7f806284bdb3")
// The link to the corresponding field data class
@FormData(value = AbstractUrlImageFieldData.class,
    sdkCommand = FormData.SdkCommand.CREATE,
    defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractUrlImageField extends AbstractImageField {

    private String url;

    @FormData
    public String getUrl() {
        return url;
    }

    @FormData
    public void setUrl(String url) {
        this.url = url;
        updateImage();
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

    protected void updateImage() {
        setImageUrl(this.url);
    }

    @Order(1000)
    @ClassId("4a2dba61-8933-4217-9f37-5683ea463ff1")
    public class EditURLMenu extends AbstractMenu {
        @Override
        protected String getConfiguredText() {
            return TEXTS.get("EditURL");
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
            return CollectionUtility.hashSet(
                ImageFieldMenuType.ImageId,
                ImageFieldMenuType.ImageUrl,
                ImageFieldMenuType.Image);
        }

        @Override
        protected void execAction() {
            PictureUrlForm form = new PictureUrlForm();
            String oldUrl = getUrl();

            // if we already have an URL for the picture prefill it in the form
            if (StringUtility.hasText(oldUrl)) {
                form.getUrlField().setValue(oldUrl);
            }

            form.startModify();
            // awaitFor makes the app wait until the user has closed the form
            form.waitFor();

            // only store the new URl if the user has saved a new value
            // storing the value will refresh the picture in the UI
            if (form.isFormStored()) {
                setUrl(form.getUrlField().getValue());
                getForm().touch();
            }
        }
    }
}

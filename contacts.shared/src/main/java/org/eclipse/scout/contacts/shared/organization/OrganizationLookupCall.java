package org.eclipse.scout.contacts.shared.organization;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

@ClassId("6bc61a50-4bd9-462b-a38f-60eec7f76d90")
public class OrganizationLookupCall extends LookupCall<String> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Class<? extends ILookupService<String>> getConfiguredService() {
        return IOrganizationLookupService.class;
    }
}

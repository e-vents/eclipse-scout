package org.eclipse.scout.contacts.shared.organization;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IOrganizationLookupService extends ILookupService<String> {

}

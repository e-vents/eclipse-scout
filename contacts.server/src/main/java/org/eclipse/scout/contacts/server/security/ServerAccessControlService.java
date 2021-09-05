package org.eclipse.scout.contacts.server.security;

import org.eclipse.scout.contacts.shared.person.CreatePersonPermission;
import org.eclipse.scout.contacts.shared.person.ReadPersonPermission;
import org.eclipse.scout.contacts.shared.person.UpdatePersonPermission;
import org.eclipse.scout.contacts.shared.security.AccessControlService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.security.DefaultPermissionCollection;
import org.eclipse.scout.rt.security.IPermissionCollection;
import org.eclipse.scout.rt.security.PermissionLevel;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;

/**
 * @author pha
 */
@Replace
public class ServerAccessControlService extends AccessControlService {

  @Override
  protected IPermissionCollection execLoadPermissions(String userId) {
    IPermissionCollection permissions = BEANS.get(DefaultPermissionCollection.class);
    permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"), PermissionLevel.ALL);
    permissions.add(new ReadPersonPermission(), PermissionLevel.ALL);
    permissions.add(new UpdatePersonPermission(), PermissionLevel.ALL);
    permissions.add(new CreatePersonPermission(), PermissionLevel.ALL);

    //TODO [pha]: Fill access control service - or replace this default implementation by simply return BEANS.get(AllPermissionCollection.class)

    permissions.setReadOnly();
    return permissions;
  }
}

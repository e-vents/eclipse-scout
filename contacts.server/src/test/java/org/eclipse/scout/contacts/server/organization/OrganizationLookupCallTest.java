package org.eclipse.scout.contacts.server.organization;

import org.eclipse.scout.contacts.shared.organization.OrganizationLookupCall;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(IServerSession.class)
public class OrganizationLookupCallTest {
// TODO [pha] add test cases

    protected OrganizationLookupCall createLookupCall() {
        return new OrganizationLookupCall();
    }

    @Test
    public void testGetDataByAll() {
        OrganizationLookupCall call = createLookupCall();
// TODO [pha] fill call
        List<? extends ILookupRow<String>> data = call.getDataByAll();
// TODO [pha] verify data
    }

    @Test
    public void testGetDataByKey() {
        OrganizationLookupCall call = createLookupCall();
// TODO [pha] fill call
        List<? extends ILookupRow<String>> data = call.getDataByKey();
// TODO [pha] verify data
    }

    @Test
    public void testGetDataByText() {
        OrganizationLookupCall call = createLookupCall();
// TODO [pha] fill call
        List<? extends ILookupRow<String>> data = call.getDataByText();
// TODO [pha] verify data
    }
}

package org.eclipse.scout.contacts.shared.person;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

@ClassId("c08a3841-a5e3-46cb-9bd8-c4bda69d0b41")
public class GenderCodeType extends AbstractCodeType<String, String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "Gender";

    @Override
    public String getId() {
        return ID;
    }

    @Order(1000)
    @ClassId("458da34a-68c7-41f5-af54-92ba4e0ce008")
    public static class MaleCode extends AbstractCode<String> {
        private static final long serialVersionUID = 1L;
        public static final String ID = "M";

        @Override
        protected String getConfiguredText() {
      return TEXTS.get("Male");
    }

        @Override
        public String getId() {
      return ID;
    }
    }

    @Order(2000)
    @ClassId("c4c6c15a-3597-4275-a414-a5b874b8ff13")
    public static class FemaleCode extends AbstractCode<String> {
        private static final long serialVersionUID = 1L;
        public static final String ID = "F";

        @Override
        protected String getConfiguredText() {
            return TEXTS.get("Female");
        }

        @Override
        public String getId() {
            return ID;
        }
    }
}

package org.wahlzeit;

import org.junit.runner.*;
import org.junit.runners.*;
import org.wahlzeit.handlers.TellFriendTest;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.*;
import org.wahlzeit.services.*;
import org.wahlzeit.services.mailing.*;
import org.wahlzeit.utils.StringUtilTest;
import org.wahlzeit.utils.VersionTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TellFriendTest.class,
    DatastoreAdapterTest.class,
    AccessRightsTest.class,
    CoordinateTest.class,
    FlagReasonTest.class,
    GenderTest.class,
    GuestTest.class,
    PhotoFilterTest.class,
    TagsTest.class,
    UserStatusTest.class,
    ValueTest.class,
    EmailServiceTest.class,
    EmailAddressTest.class,
    LogBuilderTest.class,
    StringUtilTest.class,
    VersionTest.class
})
public class AllTests{

}


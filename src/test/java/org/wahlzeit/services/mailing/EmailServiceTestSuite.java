package org.wahlzeit.services.mailing;

import org.junit.runner.*;
import org.junit.runners.*;
import org.wahlzeit.services.EmailAddressTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    EmailAddressTest.class,
    EmailServiceTest.class
})
public class EmailServiceTestSuite{

}
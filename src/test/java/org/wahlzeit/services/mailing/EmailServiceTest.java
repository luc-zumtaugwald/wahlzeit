/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.services.mailing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EmailServiceTest {

	EmailService emailService = null;
	EmailAddress validAddress = null;

	@Before
	public void setup() throws Exception {
		emailService = EmailServiceManager.getDefaultService();
		validAddress = EmailAddress.getFromString("test@test.de");
	}

	@Test
	public void testSendInvalidEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendValidEmail() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendInvalidEmailBcc() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, EmailAddress.EMPTY,"lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress,EmailAddress.EMPTY, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null,EmailAddress.EMPTY, "hi", "       "));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendValidEmailBcc() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, validAddress, "hi", "test"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}
	@Test
	public void testSendFromInvalidEmailException(){
		try{
			emailService.sendEmail(null, validAddress, "subject", " ");
			fail("Expected MailingException to be thrown");
		} catch(MailingException me){
			assertTrue(me.getMessage().contains("from must be a valid email"));
		}
	}

	@Test
	public void testSendToInvalidEmailException(){
		try{
			emailService.sendEmail(validAddress, null, "subject", " ");
			fail("Expected MailingException to be thrown");
		} catch(MailingException me){
			assertTrue(me.getMessage().contains("to must be a valid email"));
		}
		
	}

	@Test
	public void testSendValidEmailException(){
		try{
			emailService.sendEmail(validAddress, validAddress, "subject", " ");
		} catch(Exception ex){
			fail("No Exception expected");
		}
		
	}


	@Test
	public void testSendValidEmailBccException(){
		try{
			emailService.sendEmail(validAddress, validAddress, validAddress, "subject", " ");
		} catch(Exception ex){
			fail("No Exception expected");
		}
		
	}
}
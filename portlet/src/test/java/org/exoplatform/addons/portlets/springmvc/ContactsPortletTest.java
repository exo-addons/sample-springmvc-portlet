/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This file is part of Sample Spring MVC Portlet.
 *
 * Sample Spring MVC Portlet is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * Sample Spring MVC Portlet software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sample Spring MVC Portlet; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.addons.portlets.springmvc;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.portal.api.PortalTest;
import org.jboss.arquillian.portal.api.PortalURL;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@RunWith(Arquillian.class)
@PortalTest
public class ContactsPortletTest {

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap
            .create(WebArchive.class)
            .addAsLibraries(
                    Maven.resolver().loadPomFromFile("pom.xml")
                            .resolve("org.springframework:spring-webmvc-portlet:4.0.4.RELEASE", "javax.servlet:jstl:1.2")
                            .withTransitivity()
                            .asFile())
            .addClasses(Contact.class, ContactController.class, ContactService.class, ContactServiceImpl.class)
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"), "web.xml")
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/tld/portlet_2_0.tld"), "tld/portlet_2_0.tld")
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/sample-springmvc-portlet.xml"), "sample-springmvc-portlet.xml")
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/portlet.xml"), "portlet.xml")
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/jsp/views/contactForm.jsp"), "jsp/views/contactForm.jsp")
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/jsp/views/listContacts.jsp"), "jsp/views/listContacts.jsp")
            .addAsWebResource(new File("src/main/webapp/style/contacts.css"), "style/contacts.css");
  }

  @ArquillianResource
  @PortalURL
  URL portalURL;

  @Drone
  WebDriver browser;

  @Test
  @RunAsClient
  public void testContactsList() throws URISyntaxException, InterruptedException {
    // go to the contacts list page (home page)
    browser.get(portalURL.toString());

    // check that the list exists and that the 2 initial contacts have been well created
    WebElement contactsList = browser.findElement(By.id("contactsList"));
    Assert.assertNotNull("Check that contacts list exists", contactsList);
    Assert.assertTrue("Check that Mary is listed", ExpectedConditions.textToBePresentInElement(contactsList, "Mary").apply(browser));
    Assert.assertTrue("Check that John is listed", ExpectedConditions.textToBePresentInElement(contactsList, "John").apply(browser));
  }

  @Test
  @RunAsClient
  public void testGotoNewContactPage() throws URISyntaxException, InterruptedException {
    // go to the contacts list page (home page)
    browser.get(portalURL.toString());

    // click on the "New Contact" button
    WebElement newContactButton = browser.findElement(By.className("new-contact-btn"));
    Assert.assertNotNull("Check that new contact button exists", newContactButton);
    newContactButton.click();

    // check that the New Contact page is displayed
    WebElement newContactForm = browser.findElement(By.id("contactForm"));
    Assert.assertNotNull("Check that the New Contact page is displayed", newContactForm);
  }

  @Test
  @RunAsClient
  public void testDeleteContact() throws URISyntaxException, InterruptedException {
    // go to the contacts list page (home page)
    browser.get(portalURL.toString());

    // click on one of the "Delete" buttons of the list and checking if there are one less contact
    // TODO Check that the contact requested to be deleted is effectively the one deleted (how to navigate in the DOM to delete a targeted contact ?)
    WebElement contactsList = browser.findElement(By.id("contactsList"));
    Assert.assertNotNull("Check that contacts list exists", contactsList);
    List<WebElement> uiIconDeleteBeforeDeletion = contactsList.findElements(By.className("uiIconDelete"));
    int nbOfUsersBeforeDeletion = uiIconDeleteBeforeDeletion.size();

    uiIconDeleteBeforeDeletion.get(0).click();

    WebElement contactsListAfterDeletion = browser.findElement(By.id("contactsList"));
    List<WebElement> uiIconDeleteAfterDeletion = contactsListAfterDeletion.findElements(By.className("uiIconDelete"));
    Assert.assertEquals("Check that there is 1 less after deletion", uiIconDeleteAfterDeletion.size(), nbOfUsersBeforeDeletion-1);
  }


  @Test
  @RunAsClient
  public void testCreateNewContact() throws URISyntaxException, InterruptedException {
    // go directly to the new contact form page
    browser.get(portalURL.toString() + ";action=displayContactForm");

    WebElement newContactForm = browser.findElement(By.id("contactForm"));
    Assert.assertNotNull("Check that the New Contact form exists", newContactForm);

    // fill the form
    newContactForm.findElement(By.id("firstname")).sendKeys("Jack");
    newContactForm.findElement(By.id("lastname")).sendKeys("Johnson");
    newContactForm.findElement(By.id("displayName")).sendKeys("Jack Johnson");
    newContactForm.findElement(By.id("email")).sendKeys("jack.johnson@exoplatform.com");

    // click on the "Create" button
    WebElement validateNewContactButton = browser.findElement(By.id("validateNewContactButton"));
    Assert.assertNotNull("Check that the New Contact button exists", validateNewContactButton);
    validateNewContactButton.click();

    // check that the new contact is well created
    WebElement contactsList = browser.findElement(By.id("contactsList"));
    Assert.assertNotNull("Check that contacts list exists", contactsList);
    Assert.assertTrue("Check that Jack is listed", ExpectedConditions.textToBePresentInElement(contactsList, "Jack").apply(browser));
  }
}
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

import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Service managing the contacts.
 * Stores the contacts in memory, since it is only for demo purpose.
 */
@Named
public class ContactServiceImpl implements ContactService {

  /**
   * Contacts, stored only in memory
   */
  private Map<Long, Contact> contacts = new HashMap<Long, Contact>();

  /**
   * Id generator
   */
  private final Random sequence = new Random(Long.MAX_VALUE);

  @Override
  public Collection<Contact> getContacts() {
    return contacts.values();
  }

  @Override
  public Contact getContactById(Long id) {
    return contacts.get(id);
  }

  @Override
  public void createContact(Contact contact) {
    contact.setId(sequence.nextLong() + 1);
    contacts.put(contact.getId(), contact);
  }

  @Override
  public void updateContact(Contact contact) {
    contacts.put(contact.getId(), contact);
  }

  @Override
  public void deleteContact(Contact contact) {
    contacts.remove(contact.getId());
  }
}

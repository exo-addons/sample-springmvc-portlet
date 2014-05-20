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

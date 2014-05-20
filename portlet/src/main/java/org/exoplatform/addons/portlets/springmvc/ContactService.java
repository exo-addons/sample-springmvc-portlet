package org.exoplatform.addons.portlets.springmvc;

import java.util.Collection;

public interface ContactService {
  public Collection<Contact> getContacts();

  public Contact getContactById(Long id);

  public void createContact(Contact contact);

  public void updateContact(Contact contact);

  public void deleteContact(Contact contact);
}

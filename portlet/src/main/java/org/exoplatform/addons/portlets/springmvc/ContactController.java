package org.exoplatform.addons.portlets.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

/**
 * Controller
 */
@Controller
@RequestMapping("VIEW")
public class ContactController {

  private ContactService contactService;

  @Inject
  public ContactController(ContactService contactService) {
    this.contactService = contactService;

    // Init the "database" with 2 contacts
    Contact john = new Contact();
    john.setId(1L);
    john.setFirstname("John");
    john.setLastname("Smith");
    john.setDisplayName("John Smith");
    john.setEmail("john.smith@exoplatform.com");
    this.contactService.createContact(john);
    Contact mary = new Contact();
    mary.setId(2L);
    mary.setFirstname("Mary");
    mary.setLastname("Williams");
    mary.setDisplayName("Mary Williams");
    mary.setEmail("mary.williams@exoplatform.com");
    this.contactService.createContact(mary);
  }

  /**
   * Render method to display the list of contacts
   * @param request
   * @param response
   * @param modelMap
   * @return
   */
  @RenderMapping
	public ModelAndView listContacts(RenderRequest request, RenderResponse response, ModelMap modelMap) {
    ModelAndView modelAndView = new ModelAndView();

    // render listContacts.jsp
    modelAndView.setViewName("listContacts");
    // with all the contacts
    modelAndView.addObject("contacts", contactService.getContacts());

    return modelAndView;
	}

  /**
   * Render method to display the contact form
   * @param request
   * @param response
   * @return
   */
  @RenderMapping(params = "action=displayContactForm")
  public ModelAndView displayContactForm(RenderRequest request, RenderResponse response) {
    ModelAndView modelAndView = new ModelAndView();

    // render contactForm.jsp
    modelAndView.setViewName("contactForm");

    // if an id has been passed in the request, it is an update
    // otherwise, it is a creation
    String contactId = request.getParameter("id");
    if(contactId != null) {
      Long id = Long.parseLong(contactId);
      // call the service to get the selected contact and pass it to the view
      modelAndView.addObject("contact", contactService.getContactById(id));
    } else {
      modelAndView.addObject("contact", new Contact());
    }

    return modelAndView;
  }

  /**
   * Action method create or update a contact
   * @param contact
   * @param bindingResult
   * @param request
   * @param response
   */
  @ActionMapping
  public void createContact(@ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResult, ActionRequest request, ActionResponse response) {
    if(bindingResult.hasErrors()) {
      // stay on the form page
      response.setRenderParameter("action", "displayContactForm");
      if(contact != null && contact.getId() != null) {
        response.setRenderParameter("id", String.valueOf(contact.getId()));
      }
    } else {
      if(contact.getId() == null) {
        contactService.createContact(contact);
      } else {
        contactService.updateContact(contact);
      }
    }
  }

  /**
   * Action method to delete a contact
   * @param contact
   * @param bindingResult
   * @param request
   * @param response
   */
  @ActionMapping(params = "action=deleteContact")
  public void deleteContact(@ModelAttribute("contact") Contact contact, BindingResult bindingResult, ActionRequest request, ActionResponse response) {
    Long id = Long.parseLong(request.getParameter("id"));

    Contact contactToDelete = new Contact();
    contactToDelete.setId(id);

    contactService.deleteContact(contactToDelete);
  }
}

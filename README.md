Spring MVC Portlet Sample
=========================

This addon is a Spring MVC Portlet Sample.
It is a demo application that manages contacts.

The following features are used in this sample :

- Spring MVC Portlet action and render mapping
- Dependency Injection (using @Inject)
- Form binding
- Form Validation (Hibernate Validator)

Getting Started
===============

Step 1 :  Install
----------------------

Prerequisite : install [eXo Platform 4.0 Tomcat bundle](http://www.exoplatform.com/company/en/download-exo-platform) (EXO\_TOMCAT\_ROOT\_FOLDER will be used to designate the eXo Tomcat root folder).

You can install this sample by either :

- using the extension manager

__TODO_

- or building them with Maven and installing them manually

Prerequisite : install [Maven 3](http://maven.apache.org/download.html)

Clone the project with

    git clone https://github.com/exo-addons/sample-springmvc-portlet.git
    cd sample-springmvc-portlet

Build it with

    mvn clean package

Copy the extension binary with

    cp portlet/target/sample-springmvc-portlet.war EXO_TOMCAT_ROOT_FOLDER/webapps

Step 3 : Run
------------

Start eXo with

    cd EXO_TOMCAT_ROOT_FOLDER
    ./start_eXo.sh

Step 4 : Add the portlet sample in a page
-----------------------------------------

- login in your eXo instance with an administrator (root for example)
- go to the [application registry](http://docs.exoplatform.com/public/topic/PLF40/PLFUserGuide.AdministeringeXoPlatform.ManagingApplications.html?cp=1_1_10_9)
- [add the portlet sample in the application list](http://docs.exoplatform.com/public/topic/PLF40/PLFUserGuide.AdministeringeXoPlatform.ManagingApplications.ManagingPortletsAndGadgets.AddingPortletGadgetToApplicationList.html?cp=1_1_10_9_1_0)
- [add a new page](http://docs.exoplatform.com/public/topic/PLF40/PLFUserGuide.AdministeringeXoPlatform.ManagingPages.AddingNewPage.html?cp=1_1_10_6_0) or edit an existing page and add the portlet sample in that page
- save the page

Here are some screenshots :

- contacts list

![Contacts list](https://raw.githubusercontent.com/exo-addons/sample-springmvc-portlet/master/readme-resources/contacts-list.png)

- contact creation

![Contact creation](https://raw.githubusercontent.com/exo-addons/sample-springmvc-portlet/master/readme-resources/new-contact.png)

- contact update with validation errors

![Contact update](https://raw.githubusercontent.com/exo-addons/sample-springmvc-portlet/master/readme-resources/update-contact.png)

Enjoy !
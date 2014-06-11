<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<portlet:defineObjects />

<portlet:renderURL var="displayNewContactForm">
    <portlet:param name="action" value="displayContactForm"/>
</portlet:renderURL>

<link rel="stylesheet" href="<%=request.getContextPath()%>/style/contacts.css"/>

<div class="contacts-header">
    <h3>Contacts</h3>
    <a href="${displayNewContactForm}" class="btn btn-primary new-contact-btn">New Contact</a>
</div>

<table id="contactsList" class="table table-bordered">
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Display Name</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${contacts}" var="contact">
        <portlet:renderURL var="displayEditContactForm">
            <portlet:param name="action" value="displayContactForm"/>
            <portlet:param name="id" value="${contact.id}"/>
        </portlet:renderURL>
        <portlet:actionURL var="deleteContactURL">
            <portlet:param name="action" value="deleteContact"/>
            <portlet:param name="id" value="${contact.id}"/>
        </portlet:actionURL>
        <tr>
            <td>${contact.firstname}</td>
            <td>${contact.lastname}</td>
            <td>${contact.displayName}</td>
            <td>${contact.email}</td>
            <td>
                <a href="${displayEditContactForm}" data-placement="bottom" rel="tooltip" class="actionIcon" data-original-title="Edit"><i class="uiIconEdit"></i></a>
                <a href="${deleteContactURL}" data-placement="bottom" rel="tooltip" class="actionIcon" data-original-title="Delete"><i class="uiIconDelete"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>
<%--

    Copyright (C) 2003-2014 eXo Platform SAS.

    This file is part of Sample Spring MVC Portlet.

    Sample Spring MVC Portlet is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation; either version 3 of
    the License, or (at your option) any later version.

    Sample Spring MVC Portlet software is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Sample Spring MVC Portlet; if not, write to the Free
    Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
    02110-1301 USA, or see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<portlet:defineObjects />

<portlet:renderURL var="listContactsURL">
</portlet:renderURL>

<portlet:actionURL var="actionCreateContactURL" escapeXml="false">
</portlet:actionURL>

<div id="msg">
  <c:if test="${contact.id == null}">
    <h3>New Contact</h3>
  </c:if>
  <c:if test="${contact.id != null}">
    <h3>Update Contact</h3>
  </c:if>

  <form:form id="contactForm" modelAttribute="contact" action="${actionCreateContactURL}" method="POST" class="form-horizontal">

    <form:hidden path="id"></form:hidden>
    <div class="control-group">
        <label class="control-label" for="firstname">Firstname</label>
        <div class="controls">
            <form:input type="text" id="firstname" path="firstname"/>
            <form:errors path="firstname" cssClass="text-error" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="lastname">Lastname</label>
        <div class="controls">
            <form:input type="text" id="lastname" path="lastname"/>
            <form:errors path="lastname" cssClass="text-error" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="displayName">Display Name</label>
        <div class="controls">
            <form:input type="text" id="displayName" path="displayName"/>
            <form:errors path="displayName" cssClass="text-error" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="email">Email</label>
        <div class="controls">
            <form:input type="text" id="email" path="email"/>
            <form:errors path="email" cssClass="text-error" />
        </div>
    </div>

    <div class="control-group">
        <div class="controls">
            <c:if test="${contact.id == null}">
                <input id="validateNewContactButton" class="btn btn-primary" type="submit" value="Create"/>
            </c:if>
            <c:if test="${contact.id != null}">
                <input id="validateUpdateContactButton" class="btn btn-primary" type="submit" value="Update"/>
            </c:if>
            <a href="${listContactsURL}" class="btn">Cancel</a>
        </div>
    </div>
  </form:form>

</div>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="content-wrapper">   
<section class="content-header" >
        <h1>Hello World!</h1>        
</section>
<section class="content">
    <table ng-controller="contactsController">
      <tr ng-repeat="contact in page.source">
                    <td class="tdContactsCentered">{{contact.name}}</td>
      </tr>
    </table>
</section>
 <script src="<c:url value="/resources/js/pages/akses_menu.js" />"></script>
 </div>
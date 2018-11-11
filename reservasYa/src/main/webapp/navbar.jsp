<%--

  Created by IntelliJ IDEA.
  User: nahuelbarrena
  Date: 11/11/18
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">ReservasYa!</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<c:url value="/" />">Home</a></li>
                <li><a href="<c:url value="/#" />">Get started</a></li>
                <li><a href="<c:url value="/" />">Errors &amp; Validations</a></li>
                <li><a href="<c:url value="/" />">Forms</a></li>
                <li><a href="<c:url value="/" />">File Upload</a></li>
                <li><a href="<c:url value="/" />">Misc</a></li>
            </ul>
        </div>
    </div>
</div>
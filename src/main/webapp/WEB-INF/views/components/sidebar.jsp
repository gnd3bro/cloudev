<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}${model.urlMapHome}">
        <div class="sidebar-brand-icon">
            <i class="fas fa-cloud"></i>
        </div>
        <div class="sidebar-brand-text mx-3">${model.siteName}</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Profile -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}${model.urlMapUser}">
            <i class="fas fa-user-circle"></i>
            <span>Profile</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Editor -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}${model.urlMapEditor}">
            <i class="fas fa-file-code"></i>
            <span>Editor</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Overview
    </div>

    <!-- Nav Item - Repositories -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}${model.urlMapRepositories}">
            <i class="fas fa-book"></i>
            <span>Repositories</span></a>
    </li>

</ul>

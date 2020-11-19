<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/user/profile">
        <div class="sidebar-brand-icon">
            <i class="fas fa-cloud"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Cloudev</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Profile -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/user/profile">
            <i class="fas fa-user-circle"></i>
            <span>Profile</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Editor -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/user/profile">
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
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-book"></i>
            <span>Repositories</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <!-- Repo List -->
            <div id="repo_list" class="bg-white py-2 collapse-inner rounded">
            </div>
            <!-- Repo List Spinner -->
            <div id="repo_list_spinner" class="bg-white py-2 collapse-inner rounded">
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>

<!-- Sidebar Repo List -->
<script>
    $('#repo_list_spinner').html(
        '<div class="d-flex justify-content-center mb-4">\n' +
        '   <div class="spinner-border-sm" role="status">\n' +
        '       <span class="sr-only">Loading...</span>\n' +
        '   </div>\n' +
        '</div>'
    );

    $('#repo_list').append('<h6 class="collapse-header">My Repositories</h6>\n');

    $.ajax({
        type: "GET",
        url: "/user/repo_list.do",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (data) => {
            if (data.length > 0) {
                for (const idx in data) {
                    let item =
                        '<a class="collapse-item" href="/repo/'+ data[idx] +'">' + data[idx] + '</a>\n';

                    $('#repo_list').append(item);
                }
            }
            $('#repo_list_spinner').empty();
        }
    })
</script>

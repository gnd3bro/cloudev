<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container col-sm col-md-10 col-lg-8 col-xl-6">

    <!-- Repo List -->
    <div id="repo_list"></div>

    <!-- Repo List Spinner -->
    <div id="repo_list_spinner">
        <div class="d-flex justify-content-center mb-4">
            <div class="spinner-border" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
    </div>

</div>

<!-- Repo List -->
<%@include file="../components/script-repolist.jsp"%>
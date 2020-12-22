<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container col-sm col-md-10 col-lg-8 col-xl-6">

    <div id="repo_info"></div>

    <div class="card shadow mb-4">
        <div class="card-header py-3 border-bottom-secondary">
            <h6 class="m-0 font-weight-bold text-primary">${model.repoName}${model.path}</h6>
        </div>
        <div class="card-body table-responsive pl-1 pr-1 pt-0 pb-1">

            <table id="file_list" class="table table-hover m-0"></table>

            <!-- File List Spinner -->
            <div id="file_list_spinner" class="mt-4">
                <div class="d-flex justify-content-center mb-4">
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- File List -->
<%@include file="../components/script-filelist.jsp"%>

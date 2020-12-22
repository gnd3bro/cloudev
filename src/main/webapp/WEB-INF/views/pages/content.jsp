<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.1/styles/default.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.1/highlight.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlightjs-line-numbers.js/2.8.0/highlightjs-line-numbers.min.js"></script>
<style>
    /* Line Number CSS */
    /* for block of numbers */
    .hljs-ln-numbers {
        -webkit-touch-callout: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;

        text-align: center;
        border-right: 1px solid #CCC;
        vertical-align: top;
    }

    /* your custom style here */
    .hljs-ln td.hljs-ln-code {
        padding-left: 10px;
    }
</style>


<div class="container col-sm col-md col-lg col-xl">

    <div id="repo_info"></div>

    <div class="card shadow mb-4">
        <div class="card-header py-3 border-bottom-secondary">

            <div class="d-flex align-middle justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary my-auto">${model.repoName}/${model.path}</h6>
                <!-- Editor Link -->
                <a href="/editor/${model.repoName}/${model.path}" class="btn btn-secondary btn-icon-split btn-sm">
                    <span class="icon text-white-50">
                        <i class="fas fa-edit"></i>
                    </span>
                        <span class="text">
                        Edit
                    </span>
                </a>
            </div>
        </div>
        <div class="card-body table-responsive pl-2 pr-2 pt-2 pb-2">

            <pre><code id="file_content"></code></pre>

            <!-- Content Spinner -->
            <div id="file_content_spinner" class="mt-4">
                <div class="d-flex justify-content-center mb-4">
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Content -->
<%@include file="../components/script-content.jsp"%>

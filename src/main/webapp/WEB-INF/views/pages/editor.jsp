<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src='//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/codemirror.min.js'></script>
<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/codemirror.min.css'>
<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/theme/material.min.css'>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/search/searchcursor.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/search/search.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/dialog/dialog.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/edit/matchbrackets.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/edit/closebrackets.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/comment/comment.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/wrap/hardwrap.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/fold/foldcode.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/addon/fold/brace-fold.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/mode/javascript/javascript.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/5.59.0/keymap/sublime.js"></script>
<style>
    .CodeMirror {
        border-top: 1px solid #eee;
        border-bottom: 1px solid #eee;
        line-height: 1.5;
        height: auto;
        font-size: 1.2em;
    }

    .CodeMirror-linenumbers {
        padding: 0 8px;
    }
</style>

<div class="container col-sm col-md col-lg col-xl">

    <div class="card shadow mb-4">
        <div class="card-header py-3 border-bottom-secondary">

            <div class="d-flex align-middle justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary my-auto">${model.repoName}/${model.path}</h6>
                <div>
                    <!-- Return Link -->
                    <a href="/repo/${model.repoName}/${model.path}?type=file" class="btn btn-warning btn-icon-split btn-sm">
                    <span class="icon text-white-50">
                        <i class="fas fa-arrow-left"></i>
                    </span>
                        <span class="text">
                        Return
                    </span>
                    </a>

                    <!-- Save Link -->
                    <a href="" class="btn btn-success btn-icon-split btn-sm">
                    <span class="icon text-white-50">
                        <i class="fas fa-save"></i>
                    </span>
                        <span class="text">
                        Save
                    </span>
                    </a>
                </div>
            </div>
        </div>
        <div class="card-body table-responsive pl-2 pr-2 pt-2 pb-2">

            <code id="editor_content" class="text-"></code>

            <!-- Content Spinner -->
            <div id="editor_content_spinner" class="mt-4">
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
<%@include file="../components/script-editor.jsp"%>

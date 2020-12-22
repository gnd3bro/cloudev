<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script>
    $.ajax({
        type: "POST",
        url: "/file/content.do",
        data: { repoName : "${model.repoName}", fileName : "${model.path}" },
        dataType: "json",
        success: (content) => {

            $('#editor_content_spinner')[0].classList.add('collapse');

            var editor = CodeMirror(document.body.getElementsByTagName("code")[0], {
                value: content,
                scrollbarStyle: null,
                lineNumbers: true,
                keyMap: "sublime",
                autoCloseBrackets: true,
                matchBrackets: true,
                showCursorWhenSelecting: true,
                theme: "material",
                tabSize: 4
            });
        }
    });
</script>
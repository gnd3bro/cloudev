<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script>
    $.ajax({
        type: "POST",
        url: "/file/content.do",
        data: { repoName : "${model.repoName}", fileName : "${model.path}" },
        dataType: "json",
        success: (content) => {

            $('#file_content').html(content.replaceAll('<', '&lt;').replaceAll('>', '&gt;'));
            $('#file_content_spinner')[0].classList.add('collapse');
            hljs.initHighlighting();
            hljs.initLineNumbersOnLoad();
        }
    });
</script>
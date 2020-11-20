<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container col-sm col-md-10 col-lg-8 col-xl-6">

    <!-- Repo List -->
    <div id="repo_list"></div>

    <!-- Repo Spinner -->
    <div id="repo_list_spinner"></div>

    <!-- More Button -->
    <a id="repo_list_more" href="https://github.com/${model.loginId}?tab=stars" class="invisible col btn btn-secondary btn-sm shadow mb-4">
        <span class="text">
            More starred repositories...
        </span>
    </a>

</div>

<!-- Starred Repo List -->
<script>
    $('#repo_list_spinner').html(
        '<div class="d-flex justify-content-center mb-4">\n' +
        '   <div class="spinner-border" role="status">\n' +
        '       <span class="sr-only">Loading...</span>\n' +
        '   </div>\n' +
        '</div>'
    );

    $.ajax({
        type: "GET",
        url: "/repo/repo_list.do",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (data) => {
            if (data.length > 0) {
                for (const idx in data) {
                    let item =
                        '<div class="card border-left-secondary shadow h-100 py-2 mb-4">\n' +
                        '    <div class="card-body">\n' +
                        '        <div class="row no-gutters align-items-center">\n' +
                        '            <div class="col">\n' +
                        '                <div class="h4 mb-3 font-weight-bold">\n' +
                        '                    <a href="/repo/' + data[idx].name + '" class="text-secondary">\n' +
                        data[idx].name +
                        '                    </a>\n' +
                        '                </div>\n' +
                        '                <div class="h6 mb-3 text-secondary">\n' +
                        data[idx].description +
                        '                </div>\n' +
                        '                <div class="h6 mb-2">\n';

                    for (const jdx in data[idx].topicList) {
                        item +=
                            '                <a href="https://github.com/topics/' + data[idx].topicList[jdx] + '" class="btn btn-secondary btn-sm mr-1 mb-1">\n' +
                            '                    <span class="text">\n' +
                            data[idx].topicList[jdx] +
                            '                    </span>\n' +
                            '                </a>\n';
                    }

                    item +=
                        '                </div>\n' +
                        '                <div class="h6 mb-0 text-gray-600">\n' +
                        '                    <span class="icon text-secondary">\n' +
                        '                        <i class="fas fa-code"></i>\n' +
                        '                    </span>&nbsp\n' +
                        data[idx].language +
                        '                    &nbsp&nbsp&nbsp\n' +
                        '                    <span class="icon text-secondary">\n' +
                        '                        <i class="fas fa-balance-scale"></i>\n' +
                        '                    </span>&nbsp\n' +
                        data[idx].license +
                        '                    &nbsp&nbsp&nbsp\n' +
                        '                    <span class="icon text-secondary">\n' +
                        '                        <i class="fas fa-code-branch"></i>\n' +
                        '                    </span>&nbsp\n' +
                        data[idx].forksCount +
                        '                    &nbsp&nbsp&nbsp\n' +
                        '                    <span class="icon text-secondary">\n' +
                        '                        <i class="fas fa-star"></i>\n' +
                        '                    </span>&nbsp\n' +
                        data[idx].stargazersCount +
                        '                    &nbsp&nbsp&nbsp\n' +
                        '                    <a href="'+ data[idx].htmlUrl +'" class="icon text-secondary">\n' +
                        '                        <i class="fab fa-github"></i>\n' +
                        '                    </a>&nbspGithub\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>\n';

                    $('#repo_list').append(item);
                }
            }
            $('#repo_list_spinner').empty();
            $('#repo_list_more')[0].classList.remove('invisible');
        }
    })
</script>

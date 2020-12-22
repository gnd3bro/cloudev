<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script>
    $.ajax({
        type: "GET",
        url: "${model.urlMapDoRepoList}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (list) => {
            if (list.repoFullnameList.length > 0) {
                for (const idx in list.repoFullnameList) {
                    $.ajax({
                        type: "POST",
                        url: "/repo/repo.do",
                        data: { fullname : list.repoFullnameList[idx], loginId : list.loginId },
                        dataType: "json",
                        success: (repo) => {
                            if (repo != null) {
                                let item =
                                    '<div class="col-xl-auto border-left-info shadow h-100 py-2 mb-4 mr-4" >\n' +
                                    '    <div class="card-body">\n' +


                                    '                <div class="h4 mb-3 font-weight-bold">\n';

                                if (list.loginId) {
                                    item +=
                                        '                 <a href="/repo/' + repo.name + '" class="text-secondary">\n';
                                } else {
                                    item +=
                                        '                 <a href="' + repo.htmlUrl + '" class="text-secondary">\n';
                                }

                                item +=
                                    repo.name +
                                    '                    </a>\n' +
                                    '                </div>\n' +
                                    '                <div class="h6 mb-3 text-secondary">\n' +
                                    repo.description +
                                    '                </div>\n' +
                                    '                <div class="h6 mb-2">\n';

                                for (const jdx in repo.topicList) {
                                    item +=
                                        '                <a href="https://github.com/topics/' + repo.topicList[jdx] + '" class="btn btn-secondary btn-sm mr-1 mb-1">\n' +
                                        '                    <span class="text">\n' +
                                        repo.topicList[jdx] +
                                        '                    </span>\n' +
                                        '                </a>\n';
                                }

                                item +=
                                    '                </div>\n' +
                                    '                <div class="h6 mb-0 text-gray-600">\n' +
                                    '                    <span class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-code fa-sm"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.language +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <span class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-balance-scale fa-sm"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.license +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <span class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-code-branch fa-sm"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.forksCount +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <span class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-star fa-sm"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.stargazersCount +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <a href="' + repo.htmlUrl + '" target="_blank" class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-external-link-alt fa-sm"></i>\n' +
                                    '                    </a>\n' +


                                    '        </div>\n' +
                                    '    </div>\n' +
                                    '</div>\n';

                                $('#repo_list').append(item);
                            }

                            if (list.repoFullnameList.length - 1 === parseInt(idx)) {
                                $('#repo_list_spinner')[0].classList.add('collapse');
                            }
                        }
                    });
                }
            }
        }
    });
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script>
    $.ajax({
        type: "POST",
        url: "/file/list.do",
        data: { repoName : "${model.repoName}", path : "${model.path}" },
        dataType: "json",
        success: (list) => {
            if (list.length > 0) {
                let item =
                    '<body>';

                for (const idx in list) {
                    item +=
                        '<tr onclick="location.href=\'/repo/${model.repoName}${model.path}/' +
                        list[idx].name + '\'">\n' +
                        '    <td>' +
                        '        <span class="icon text-secondary">';
                    if (list[idx].type === "file") {
                        item += '<i class="far fa-file"></i>';
                    } else if (list[idx].type === "dir") {
                        item += '<i class="fas fa-folder"></i>';
                    } else {
                        item += '<i class="fas fa-question"></i>';
                    }
                    item +=
                        '        </span>&nbsp' +
                        list[idx].name +
                        '    </td>\n' +
                        '    <td class="text-right">';

                    if (list[idx].type === "file") {
                        item += list[idx].size;
                    }

                    item +=
                        '    </td>\n' +
                        '</tr>';

                    /*$.ajax({
                        type: "POST",
                        url: "/file/file.do",
                        data: { repoName : "${model.repoName}", fileName : list[idx] },
                        dataType: "json",
                        success: (repo) => {
                            if (repo != null) {
                                let item =
                                    '<div class="card border-left-secondary shadow h-100 py-2 mb-4">\n' +
                                    '    <div class="card-body">\n' +
                                    '        <div class="row no-gutters align-items-center">\n' +
                                    '            <div class="col">\n' +
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
                                    '                        <i class="fas fa-code"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.language +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <span class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-balance-scale"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.license +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <span class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-code-branch"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.forksCount +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <span class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-star"></i>\n' +
                                    '                    </span>&nbsp\n' +
                                    repo.stargazersCount +
                                    '                    &nbsp&nbsp&nbsp\n' +
                                    '                    <a href="' + repo.htmlUrl + '" target="_blank" class="icon text-secondary">\n' +
                                    '                        <i class="fas fa-external-link-alt"></i>\n' +
                                    '                    </a>\n' +
                                    '                </div>\n' +
                                    '            </div>\n' +
                                    '        </div>\n' +
                                    '    </div>\n' +
                                    '</div>\n';

                                $('#repo_list').append(item);
                            }

                            if (list.repoFullnameList.length - 1 === parseInt(idx)) {
                                $('#repo_list_spinner')[0].classList.add('collapse');
                            }
                        }
                    });*/

                }

                $('#file_list').html(item).append('</tbody>');
                $('#file_list_spinner')[0].classList.add('collapse');
            }
        }
    });
</script>
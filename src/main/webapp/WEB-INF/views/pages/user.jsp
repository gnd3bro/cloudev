<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container col-sm col-md-10 col-lg-8 col-xl-6">

    <!-- Avatar Image -->
    <div class="d-flex justify-content-center">
        <img class="col-auto p-2 img-profile rounded-circle shadow" src="${model.avatarUrl}">
    </div>

    <!-- Github Link -->
    <a href="${model.profileUrl}" class="btn btn-primary btn-icon-split btn-sm mb-2 ml-1">
        <span class="icon text-white-50">
            <i class="fab fa-github"></i>
        </span>
        <span class="text">
            Github
        </span>
    </a>

    <!-- Username Card -->
    <div class="card border-bottom-primary shadow h-100 py-2 mb-4">
        <div class="card-body">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2">
                    <!-- Username -->
                    <div class="h3 font-weight-bold text-primary text-uppercase mb-1">
                        ${model.username}
                    </div>
                    <!-- Login ID -->
                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                        ${model.loginId}
                    </div>
                </div>
                <div class="col mr-0">
                    <!-- Bio -->
                    <div class="h5 font-weight-bold text-right text-secondary mb-1">
                        ${model.bio}
                    </div>
                    <!-- Follower -->
                    <div class="h6 mb-0 font-weight-bold text-right text-secondary">
                        <span class="text-gray-800 font-weight-bolder">
                            ${model.followersCount}
                        </span>
                        Followers
                    </div>
                    <!-- Following -->
                    <div class="h6 mb-0 font-weight-bold text-right text-secondary">
                        <span class="text-gray-800 font-weight-bolder">
                            ${model.followingCount}
                        </span>
                        Following
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Cards Container -->
    <div class="row m-0">

        <!-- Blog & Twitter Card -->
        <div class="col-xl card border-left-info shadow h-100 py-2 mb-4">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <!-- Blog -->
                        <div class="h6 font-weight-bold mb-2">
                            <span class="icon text-secondary">
                                <i class="fas fa-link"></i>
                            </span>
                            <a href="${model.blogUrl}">
                                ${model.blogUrl}
                            </a>
                        </div>
                        <!-- Twitter -->
                        <div class="h6 font-weight-bold mb-0">
                            <span class="icon text-secondary">
                                <i class="fab fa-twitter"></i>
                            </span>
                            <a href="https://twitter.com/${model.twitterUsername}">
                                ${model.twitterUsername}
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Spacer -->
        <div class=".d-lg-none .d-xl-block mr-4"></div>

        <!-- Bio & Email Card -->
        <div class="col-xl card border-left-info shadow h-100 py-2 mb-4">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <!-- Location -->
                        <div class="h6 font-weight-bold mb-2">
                            <span class="icon text-secondary">
                                <i class="fas fa-map-marker-alt"></i>
                            </span>
                            ${model.location}
                        </div>
                        <!-- Email -->
                        <div class="h6 mb-0 font-weight-bold text-secondary text-gray-800">
                            <span class="icon text-secondary">
                                <i class="fas fa-at"></i>
                            </span>
                            <a href="mailto:${model.email}">
                                ${model.email}
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- End of Cards Container -->

    <!-- Starred Repo Title -->
    <div class="col-xl card border-bottom-success shadow h-100 py-2 mb-4">
        <div class="card-body pt-2 pb-0 pl-2 pr-0">
            <div class="row no-gutters align-items-center">
                <div class="col">
                    <div class="h6 font-weight-bold">
                        Starred Repositories
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Starred Repo List -->
    <div id="repo_list"></div>

    <!-- Starred Repo Spinner -->
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
        url: "/user/starred_list.do",
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
                        '                    <a href="' + data[idx].htmlUrl + '" class="text-secondary">\n' +
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

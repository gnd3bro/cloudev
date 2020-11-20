<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container col-sm col-md-10 col-lg-8 col-xl-6">

    <div id="repo_info"></div>

    <div class="card shadow mb-4">
        <div class="card-header py-3 border-bottom-secondary">
            <h6 class="m-0 font-weight-bold text-primary">${model.repoName}</h6>
        </div>
        <div class="card-body table-responsive pl-1 pr-1 pt-0 pb-1">
            <table id="file_list" class="table table-hover m-0">
                <tbody>
                <tr>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <td colspan="2">Larry the Bird</td>
                    <td>@twitter</td>
                </tr>
                </tbody>
            </table>

            <!-- Starred Repo Spinner -->
            <div id="file_list_spinner"></div>
        </div>
    </div>

</div>

<!-- File List -->
<script>
    $('#file_list_spinner').html(
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
                        '<tr>\n' +
                        '    <td>'+ +'</td>\n' +
                        '    <td>'+ +'</td>\n' +
                        '    <td>'+ +'</td>\n' +
                        '</tr>';

                    $('#file_list').append(item);
                }
            }
            $('#file_list_spinner').empty();
        }
    })
</script>

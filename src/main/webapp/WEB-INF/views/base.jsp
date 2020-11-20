<%@ page import="org.springframework.web.util.UrlPathHelper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% String url = new UrlPathHelper().getOriginatingRequestUri(request); %>
<!DOCTYPE html>
<html lang="en">

    <%@include file="components/head.jsp"%>
    <%@include file="components/scripts-header.jsp"%>

    <body id="page-top" class="toggled">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="components/sidebar.jsp"%>

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@include file="components/topbar.jsp"%>

                    <!-- Page Content -->
                <% if (url.equals("/user")) { %>

                    <%@include file="pages/user.jsp"%>

                <% } else if (url.equals("/repo")) { %>

                    <%@include file="pages/repo.jsp"%>

                <% } else if (url.contains("/repo/")) { %>

                    <%@include file="pages/files.jsp"%>

                <% } else { %>

                    <%@include file="pages/error.jsp"%>

                <% } %>

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <%@include file="components/footer.jsp"%>

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <%@include file="components/logout_modal.jsp"%>

        <!-- Footer Script -->
        <%@include file="components/scripts-footer.jsp"%>

    </body>

</html>
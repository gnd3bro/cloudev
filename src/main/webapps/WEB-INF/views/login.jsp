<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <%@include file="components/head.jsp"%>

    <body class="bg-gradient-primary">

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-xl-5 col-lg-6 col-md-8">
                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="p-5">

                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Login</h1>
                                        </div>

                                        <form class="user"
                                              action="${pageContext.request.contextPath}/login.do"
                                              method="post">

                                        <!--<div class="form-group">

                                                <input type="text"
                                                       class="form-control form-control-user"
                                                       name="username"
                                                       placeholder="Username">

                                            </div>-->

                                            <div class="form-group">
                                                <input type="password"
                                                       class="form-control form-control-user"
                                                       name="token"
                                                       placeholder="Access Token">
                                            </div>

                                            <input type="submit"
                                                   class="btn btn-primary btn-user btn-block"
                                                   value="Login">

                                        </form>

                                        <hr>

                                        <div class="text-center">
                                            <a class="small"
                                               href="https://github.com/settings/tokens">
                                                Forgot Access Token?
                                            </a>
                                        </div>

                                        <div class="text-center">
                                            <a class="small"
                                               href="https://github.com/join?source=login">
                                                Create a Github account!
                                            </a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="components/scripts-header.jsp"%>

    </body>

</html>
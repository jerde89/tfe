<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/feedbacklist.css">
    <meta th:name="_csrf" th:content="${_csrf.token}"/>
    <meta th:name="_csrf_header" th:content="${_csrf.headerName}"/>
    <script src="${pageContext.request.contextPath}/js/feedbackList.js"></script>
    <script type="text/javascript">
        var pageContextPath = "${pageContext.request.contextPath}";
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
    </script>
</head>
<body>

<%@include file="navbar.jsp" %>

<div class="container">

    <div>
        <h1 style="text-align: center">Gestion des feedbacks</h1>
        <ul class="nav nav-tabs" role="tablist">
            <li class="active">
                <a href="#tab-table1" data-toggle="tab">Feedback Non Lu</a>
            </li>
            <li>
                <a href="#tab-table2" data-toggle="tab">Feedback Lu</a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active m-4" id="tab-table1">
                <table class="table table-striped table-bordered" id="feedback_non_lus_ajax" cellspacing="0"
                       width="100%">
                    <thead>
                    <tr>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th>Commentaire</th>
                        <th>Date creation</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane m-4" id="tab-table2">
                <table class="table table-striped table-bordered" id="feedback_lus_ajax" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Commentaire</th>
                        <th>Date creation</th>
                        <th>Lu le</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

    <!--Popup pour voir les feedbacks-->
    <div id ="contentSeeFeedback" class="contentSeeFeedbacks"  title="Feedback"  >
        <div class="pb-3" align="left">
            <label><b>Nom : </b></label>
            <a id="nameFeedback"> </a>
        </div>

        <div class="pb-3" align="left">
            <label><b>Prénom : </b></label>
            <a id="firstnameFeedback"> </a>
        </div>

        <div class="pb-3" align="left">
            <label><b>Email : </b></label>
            <a id="email"> </a>
        </div>

        <div class="pb-3" align="left">
            <label><b>Téléphone : </b></label>
            <span id="phone"></span>
        </div>
        <div class="pb-3" align="left">
            <label><b>Reçu le : </b></label>
                <span id="created"></span>
        </div>

        <div class="mb-3 pb-3" align="left">
            <label for="comment" class="form-label"><b>Commentaire :</b></label>
            <div class="form-control" id="comment" ></div>
        </div>
<%--        <button onclick="togglePopup()" class="btn btn-primary" style="background-color:#F0BF72">--%>
<%--            Fermer--%>
<%--        </button>--%>
    </div>

    <!--Popup de confirmation de suppression des feedbacks-->
    <div class="contentConfirmationDeleteFeedback">
        <div class="pb-1">
            <h3>Etes vous sûr de vouloir supprimer ce feedback</h3>
        </div>

        <input type="hidden" id="deleteFeedBackIdHidden"/>

        <button onclick="callAjaxDeleteFeedback()" class="btn btn-primary" style="background-color:#F0BF72">
            Oui
        </button>
        <button onclick="togglePopupDeleteFeedback()" class="btn btn-primary" style="background-color:#F0BF72">
            Non
        </button>
    </div>

    <input type="hidden" name="feedbackId" value="${feedback.feedbackId}" data-placement="top"/>
</div>

<input type="hidden" id='_csrf' name="csrf" value="${_csrf.token}">
</body>

<c:import url="footer.jsp"/>


</html>




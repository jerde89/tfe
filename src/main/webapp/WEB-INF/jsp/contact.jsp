<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Contact</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
    <script src="${pageContext.request.contextPath}/js/contact.js"></script>
</head>
<body>
<div class="containerHeader">
    <%@include file="navbar.jsp" %>
</div>

<div class="containerBody">
    <div class="container">
        <div class="row row-cols-1 row-cols-md-3 g-4 py-5">
            <div class="col">
                <div class="card pt-4" style="background: #f2f3f4; ">
                    <p class="pt-3" style="text-align: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor"
                             class="bi bi-telephone d-inline-block align-text-top" viewBox="0 0 16 16">
                            <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
                        </svg>
                    </p>
                    <div class="card-body">
                        <h4 style="text-align: center;">
                            <a id="lien2" style="color: #F0BF72" href="tel:071/39.77.13">071/39.77.13</a>
                        </h4>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card pt-4" style="background: #f2f3f4; ">
                    <p class="pt-3" style="text-align: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor"
                             class="bi bi-geo-alt" viewBox="0 0 16 16">
                            <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/>
                            <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                        </svg>
                    </p>
                    <div class="card-body">
                        <h4 style="text-align: center; color: #F0BF72">Rue des gonceries 75</h4>
                        <h4 style="text-align: center; color: #F0BF72">6032 Mont-sur-Marchienne</h4>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card pt-4" style="background: #f2f3f4; ">
                    <p class="pt-3" style="text-align: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor"
                             class="bi bi-envelope" viewBox="0 0 16 16">
                            <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"/>
                        </svg>
                    </p>
                    <div class="card-body">
                        <h4 style="text-align: center;">
                            <a id="lien" style="color: #F0BF72" href="mailto:lefournildesgonceries@gmail.com">lefournildesgonceries@gmail.com</a>
                        </h4>
                    </div>
                </div>
            </div>


        </div>
    </div>


    <div class="container">
        <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2543.821289952407!2d4.412185815894415!3d50.38852989970561!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47c22434d4bdeb55%3A0xf080cae1174fe3e4!2sRue%20des%20Gonceries%2075%2C%206032%20Charleroi!5e0!3m2!1sfr!2sbe!4v1640184550634!5m2!1sfr!2sbe"
                width=100% height="350" style="border: 0;" allowfullscreen="" loading="lazy"></iframe>
    </div>


    <div class="container" style="padding-top:10px; padding-bottom:100px;">

        <div class="row pt-4">
            <div class="col-md-6 ">
                <div class="col" style="padding-top:20px; padding-bottom:20px">
                    <div class="text-center fw-bold"><h3>Heures d'ouverture</h3></div>
                </div>

                <div class="row justify-content-center lh-lg">
                    <!--ps-5 => p=padding s=� gauche 5=largeur (plus large)-->
                    <div class="col-6 ps-5">
                        <div>Lundi</div>
                        <div>Mardi</div>
                        <div>Mercredi</div>
                        <div>Jeudi</div>
                        <div>Vendredi</div>
                        <div>Samedi</div>
                        <div>Dimanche</div>
                    </div>

                    <div class="col-6 pe-5 text-end">
                        <div>07:00 - 18:00</div>
                        <div>07:00 - 18:00</div>
                        <div>07:00 - 18:00</div>
                        <div>--- Fermé ---</div>
                        <div>07:00 - 18:00</div>
                        <div>07:00 - 18:00</div>
                        <div>07:00 - 14:00</div>
                    </div>
                </div>
            </div>

            <div class="col-md-6 ms-auto text-center pt-5">
                <div class="zoom">
                    <div class="zoom :hover">
                        <img src="${pageContext.request.contextPath}/image/Profil.png" alt="Image des gérants" width="40%"
                             alt="Profil">
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- ms = marge gauche   me = marge droite  mx = marge des deux c�t�s-->

    <div class="container pt-3 pb-3" style="background: #f2f3f4;">

        <c:if test="${not empty sessionScope.errors}">
            <div class="alert alert-danger" role="alert">
                <c:forEach items="${sessionScope.errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </div>
            <c:remove var="errors" scope="session"></c:remove>
        </c:if>



        <c:if test="${not empty sessionScope.success}">
            <script>
                $.toast(
                    {
                        heading: 'Félicitations',
                        text: 'Votre formulaire a été envoyé avec succès',
                        showHideTransition: 'slide',
                        icon: 'success',
                        position: 'top-right',
                        stack: false,
                    }
                );
            </script>

            <c:remove var="success" scope="session"></c:remove>
        </c:if>

    <div class="container align-items-center col-8 ">
        <div class="col-12">
                <form class="shadow p-4 bg-white rounded" name="contactFeedback"
                      method="post" action="${pageContext.request.contextPath}/contact/sendFeedback"
                      onsubmit="return validateContactFeedback()">

                    <div class="mt-2 mb-5 text-center">
                        <h3 class="text-black">Formulaire de contact</h3>
                        <h6 class="text-black-50">Veuillez entrer les champs obligatoires (*)</h6>
                    </div>


                    <div class="row mx-2">
                        <div class="col-6">
                            <label for="InputName" class="form-label text-black">Nom*</label>
                            <input type="text" class="form-control" id="InputName" name="nameFeedback" placeholder="Nom" onblur="checkName()">
                            <div class="span-error-div"><span class="span-error4" id="errorNameFeedback"></span></div>
                        </div>

                        <div class="col-6">
                            <label for="InputFirstname" class="form-label text-black">Prénom*</label>
                            <input type="text" class="form-control" id="InputFirstname" name="firstnameFeedback"
                                   placeholder="Prénom" onblur="checkFirstname()">
                            <div class="span-error-div"><span class="span-error4" id="errorFirstnameFeedback"></span></div>
                        </div>
                    </div>


                    <div class="row mx-2">
                        <div class="col-12">
                            <label class="form-label text-black">Adresse email *</label>
                            <input type="text" class="form-control" id="InputEmail" name="emailFeedback"
                                   placeholder="Email" onblur="checkEmail()">
                            <div class="span-error-div"><span class="span-error4" id="errorEmailFeedback"></span></div>
                        </div>
                    </div>

                    <div class="row mx-2">
                        <div class="col-12">
                            <label class="form-label text-black">Téléphone*</label>
                            <input type="text" class="form-control" id="InputPhone" name="phoneFeedback"
                                   placeholder="Téléphone">
                            <div class="span-error-div"><span class="span-error4" id="errorPhoneFeedback"></span></div>
                        </div>
                    </div>

                    <div class="row mx-2">
                        <div class="col-12">
                            <label class="form-label">Commentaire*</label>
                            <textarea class="form-control" id="InputCommentFeedback" name="commentFeedback" rows="3"
                                      placeholder="Commentaire"></textarea>
                            <div class="span-error-div"><span class="span-error4" id="errorCommentFeedback"></span></div>
                        </div>
                    </div>

                    <%--                <div class="divSubmit">--%>
                    <%--                    <button type="submit" class="btn btn-primary" style="background-color:#F0BF72">Envoyer le formulaire</button>--%>
                    <%--                </div>--%>

                    <div class="form-group" style="text-align:center">
                        <button id="submitBtn" type="submit" class="form-control btn btn-primary rounded submit px-3">
                            Envoyer le formulaire
                        </button>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
            <!--  </form>-->
        </div>
    </div>
</div>

<div class="containerFooter">
    <%@include file="footer.jsp" %>
</div>
</body>

</html>
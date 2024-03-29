<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@include file="head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome.css">
    <script id="Cookiebot" src="https://consent.cookiebot.com/uc.js" data-cbid="e3a73d74-61bb-46cc-8c7f-140ca9e2949b" data-blockingmode="auto" type="text/javascript"></script>

</head>
<body>
    <%@include file="navbar.jsp" %>


    <h1 style="text-align: center">Bienvenue au Fournil des Gonceries</h1>

    <div class="container align-items-center col-8" >

        <p>At nunc si ad aliquem bene nummatum tumentemque ideo honestus advena salutatum introieris, primitus tamquam exoptatus suscipieris et interrogatus multa coactusque mentiri, miraberis numquam antea visus summatem virum tenuem te sic enixius observantem, ut paeniteat ob haec bona tamquam praecipua non vidisse ante decennium Romam.</p>

        <p>Equitis Romani autem esse filium criminis loco poni ab accusatoribus neque his iudicantibus oportuit neque defendentibus nobis. Nam quod de pietate dixistis, est quidem ista nostra existimatio, sed iudicium certe parentis; quid nos opinemur, audietis ex iuratis; quid parentes sentiant, lacrimae matris incredibilisque maeror, squalor patris et haec praesens maestitia, quam cernitis, luctusque declarat.</p>

        <p>Principium autem unde latius se funditabat, emersit ex negotio tali. Chilo ex vicario et coniux eius Maxima nomine, questi apud Olybrium ea tempestate urbi praefectum, vitamque suam venenis petitam adseverantes inpetrarunt ut hi, quos suspectati sunt, ilico rapti conpingerentur in vincula, organarius Sericus et Asbolius palaestrita et aruspex Campensis.</p>
    </div>

    <div class="col ms-auto text-center pt-6">
        <div class="zoom">
            <div class="zoom :hover">
                <img src="${pageContext.request.contextPath}/image/MaitreBoulanger.png" alt="Image des gérants"  width="20%" alt="Profil">
            </div>
        </div>
    </div>
    <script id="CookieDeclaration" src="https://consent.cookiebot.com/e3a73d74-61bb-46cc-8c7f-140ca9e2949b/cd.js" type="text/javascript" async></script>
    <c:import url="footer.jsp"/>
</body>

</html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Christopher Meier; Daniel Palumbo">

    <title>AMT - Bootcamp</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/css/heroic-features.css"/>" rel="stylesheet">
</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
            <a class="navbar-brand" href="<c:url value="/"/>">AMT - Bootcamp</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="<c:url value="/home"/>">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/manage_quotes"/>">Manage Quotes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/configuration"/>">Configuration</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <c:set var="alerts" value="${requestScope.alerts}" />
    <c:if test="${alerts != null}" >
        <div id="alertCarousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <c:forEach var="alertIndic" items="${alerts}" varStatus="stat">
                    <li class="bg-${alertIndic.level} <c:if test="${stat.first}" >active</c:if>"
                    data-target="#alertCarousel"
                    data-slide-to="${stat.index}" ></li>
                </c:forEach>
            </ol>
            <div class="carousel-inner">
                <c:forEach var="alert" items="${alerts}" varStatus="stat">
                    <div class="carousel-item <c:if test="${stat.first}" >active</c:if>">
                        <div class="alert alert-${alert.level}" role="alert">
                            <div class="container">
                                <h4 class="alert-heading mt-4">${alert.title}</h4>
                                <p>${alert.message}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#alertCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#alertCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </c:if>



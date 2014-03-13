<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="<spring:url value="/resources/vendor/css/normalize-2.1.3.css"/>" media="all"/>
    <link rel="stylesheet" href="<spring:url value="/resources/vendor/css/boilerplate-4.2.0.css"/>" media="all"/>
    <link rel="stylesheet" href="<spring:url value="/resources/vendor/css/bootstrap-3.0.0.css"/>" media="all"/>
    <link rel="stylesheet" href="<spring:url value="/resources/vendor/css/bootstrap-theme-3.0.0.css"/>" media="all"/>

    <script type="text/javascript" src="<spring:url value="/resources/vendor/js/jquery-2.0.3.js"/>"></script>
    <script type="text/javascript" src="<spring:url value="/resources/vendor/js/jquery.tinypubsub.js"/>"></script>
    <script type="text/javascript" src="<spring:url value="/resources/vendor/js/bootstrap-3.0.0.js"/>"></script>

    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>

<body>
<div class="container">

    <h2><ftm:message key="label.applicationName"/></h2>

    <form:form method="post" commandName="translateModel">
        <form:errors path="*" cssClass="errorblock" element="div"/>
        <div class="row">
            <div class="col-xs-6">
            </div>

            <div class="col-xs-6">
                <form:select path="destinationLanguage" cssClass="form-control">
                    <form:errors path="destinationLanguage" cssClass="error"/>
                    <form:option value="NONE" label="--- Select ---"/>
                    <form:options items="${supportedLanguages}"/>
                </form:select>
            </div>
        </div>

        <div class="row" style="padding-top: 20px">
            <div class="col-xs-6">
                <div class="panel panel-default clearfix">
                    <div class="panel-heading" charset="utf-8"><ftm:message key="label.source"/>
                    </div>
                    <div class="panel-body">
                        <form:textarea class="form-control" path="source"
                                       style="width: 100%;height: 300px;"/>${translatemodel.source}
                        <form:errors path="source" cssClass="error"/>
                    </div>
                </div>
            </div>

            <div class="col-xs-6">
                <div class="panel panel-default">
                    <div class="panel-heading" charset=utf-8><ftm:message key="label.result"/></div>
                    <div class="panel-body">
                        <form:textarea path="result" class="form-control" charset="utf-8"
                                       style="width: 100%;height: 300px;"/>${translatemodel.result}
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6 clearfix">
                <button type="submit" class="btn btn-primary pull-right">
                    <span class="glyphicon glyphicon-tasks"></span> <ftm:message key="label.translate"/>
                </button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
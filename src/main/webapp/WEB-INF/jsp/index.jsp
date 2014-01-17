<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
</head>

<body>
<div class="container">

    <h2>Property Translator</h2>

    <form method="post" action="<spring:url value="/index.html"/>">
        <div class="row">
            <div class="col-xs-6">
                <div class="panel panel-default clearfix">
                    <div class="panel-heading">Source
                    </div>
                    <div class="panel-body">
                        <textarea class="form-control" name="source"
                                  style="width: 100%;height: 300px;">${source}</textarea>
                    </div>
                </div>
            </div>

            <div class="col-xs-6">
                <div class="panel panel-default">
                    <div class="panel-heading">Result</div>
                    <div class="panel-body">
                        <textarea name="result" class="form-control"
                                  style="width: 100%;height: 300px;">${result}</textarea>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6 clearfix">
                <button type="submit" class="btn btn-primary pull-right">
                    <span class="glyphicon glyphicon-tasks"></span> Translate
                </button>
            </div>
        </div>
    </form>
</div>
</body>
</html>

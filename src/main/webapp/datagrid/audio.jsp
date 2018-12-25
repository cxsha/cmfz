<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">
    $("#audio").prop("src", "${pageContext.request.contextPath}/audio/" + cUrl);
</script>
<audio id="audio" controls="controls"></audio>


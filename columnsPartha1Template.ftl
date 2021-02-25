<!doctype html>
<html>
<head>
  <title>Charlie's Magic</title>
</head>
<body>
 

    <#list systems as system>
<p>${system_index + 1}</p>
<p>${system.auditId},
${system.objectId},
${system.objectTitle},
${system.objectType},
${system.stage},
${system.commChnl},
${system.commLang},
${system.plndStrtDt},
${system.plndEndDt},
${system.actlEndDt},
${system.status},
${system.mjrNc},
${system.nextRevDt},
${system.auditee},
${system.auditeeName},
${system.auditeeEmail},
${system.auditeeLocations},
${system.auditeeTimezone},
${system.creBy},
${system.creAt},
${system.updBy},
${system.updAt}
</p>
</#list>

</body>
</html>
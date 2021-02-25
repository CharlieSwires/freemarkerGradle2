<!doctype html>
<html>
<head>
  <title>${title}</title>
</head>
<body>
  <table width="100%"><tr><td><h2 align="left">${printedby}</h2></td>
  <td><h2 align="right">${datetime}</h2></td></tr>
  </table>
  <br>
  <h1 align="center">${title}</h1>

<table width="100%" border="4px">
    <#list headings as heading>
      <tr>
      <th border="1px" width="5%">row</th>
      <th border="1px">${heading.col0}</th>
      <th border="1px">${heading.col1}</th>
      <th border="1px">${heading.col2}</th>
      <th border="1px">${heading.col3}</th>
      <th border="1px">${heading.col4}</th>
      </tr>
    </#list>
    <#list systems as system>
      <tr>
      <td border="1px">${system_index + 1}</td>
      <td border="1px">${system.col0}</td>
      <td border="1px">${system.col1}</td>
      <td border="1px">${system.col2}</td>
      <td border="1px">${system.col3}</td>
      <td border="1px">${system.col4}</td>
      </tr>
    </#list>
</table>
</body>
</html>
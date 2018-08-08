<!DOCTYPE html>
<html lang="en">
<head>
<title>Welcome</title>
<#include "partials/style.ftl">
</head>
<body>
<table cellpadding="0" cellspacing="0" border="0" width="100%" style="background:#f9f9f9;">
<tr>
<td style="padding:padding:15px 0 40px 0;">
  <#include "partials/header.ftl">
  
  <table width="574" align="center" cellpadding="0" cellspacing="0" border="0" class="ecxwidth311" style="text-align:left;">
    <tr>
      <td style="padding:0;padding-left:20px;padding-right:20px;background:#ffffff;">
        <h1>Welcome to ASCENDING, ${user.username}!</h1>
        <p align="left">
          You have successfully signed up for ascending demo!
        </p>
      </td>
    </tr>
  </table>

  <#include "partials/footer.ftl">
</td>
</tr>
</table>
</html>
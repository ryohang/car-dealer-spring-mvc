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
        <h1>Welcome to Snaapiq, ${user.username}!</h1>
        <p align="left">
          You have successfully signed up for Snaapiq! Welcome to our community of snaapers.
          We look forward to seeing your very first snaap, where you'll begin earning rewards
          almost immediately!
        </p>

        <p align="left">Here are a few things you should absolutely do when you first log in:</p>

        <h3>1. Play in a few contests that you like</h3>
        <p align="left">We add multiple contests daily and send prizes right to your phones. Don't miss out on all the fun contests we have!</p>

        <h3>2. Rate pictures</h3>
        <p align="left">Every time you rate a picture, you earn Snaapcoins...which can be used to get you rewards form our marketplace!</p>

        <h3>3. Check out our marketplace</h3>
        <p align="left">You can see all the goodies in our marketplace by clicking the "shopping cart" icon on the bottom navigation</p>

        <p><a href="${activationurl}" class="red-link">Please activate account to start winning prizes!</a></p>
      </td>
    </tr>
  </table>

  <#include "partials/footer.ftl">
</td>
</tr>
</table>
</html>
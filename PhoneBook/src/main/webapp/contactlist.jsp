<%@ page import="java.util.*, com.tka.Contact" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Contact List</title>
  <link rel="stylesheet" href="style.css">
  <link href="https://cdn.jsdelivr.net/npm/remixicon/fonts/remixicon.css" rel="stylesheet">
</head>
<body>
  <div class="container">
    <h1>Contact List</h1>

<%
  List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
  if (contacts != null && !contacts.isEmpty()) {
    for (Contact con : contacts) {
%>
      <div class="contact-card">
        <div class="info">
          <h2><%= con.getName() %></h2>
          <p><%= con.getPhone() %></p>
        </div>
        <div class="icons">
          <a href="tel:<%= con.getPhone() %>" class="call"><i class="ri-phone-line"></i></a>
          <a href="sms:<%= con.getPhone() %>" class="message"><i class="ri-chat-3-line"></i></a>
        </div>
      </div>
<%
    }
  } else {
%>
    <p>No contacts available.</p>
<%
  }
%>

    <div style="text-align: center; margin-top: 30px;">
      <a href="register.html">Add New Contact</a>
    </div>
  </div>
</body>
</html>

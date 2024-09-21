<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>HelpDesk Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="CSS/login.css">
 
   
  
</head>
<body>

  <div class="split-page">
    <!-- Left side for login -->
    <div class="left-half">
      <div class="login-container">
        <div class="login-logo">
          <img src="https://via.placeholder.com/120" alt="HelpDesk Logo">
        </div>
        <h2>HelpDesk Login</h2>
        <form action="userControllerServlet" method="POST">
        
        <input type="hidden" name="command" value="CHECK">
          <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" name="email" required placeholder="Enter your email">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="pw" required placeholder="Enter your password">
          </div>
          <br>
          <button type="submit" class="btn btn-custom w-100">Login</button>
        </form>
        
        <% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <div style="color: red;">
        <%= errorMessage %>
    </div>
<% 
    }
%>
        
      </div>
    </div>

    <!-- Right side for image -->
    <div class="right-half">
      <img src="img/login.svg" alt="Right Side Image">
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

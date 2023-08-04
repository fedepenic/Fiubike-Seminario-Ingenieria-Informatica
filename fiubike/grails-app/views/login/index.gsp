<!DOCTYPE html>
<html>
<head>
    <title>Fiubike</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0; 
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh; 
            background-color: #f8f8f8; 
        }
        .container {
            max-width: 400px;
            width: 100%;
            padding: 0 15px; 
        }
        .login-form {
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
            display:flex;
            flex-direction:column;
        }
        .form-label {
            display: block;
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        .form-submit {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
        .form-submit:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-form">
            <h2>Login</h2>
            <form action="${createLink(uri: '/login/redirect')}" method="post">
                <div class="form-group">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" name="username" id="username" class="form-input" required>
                </div>
                <div class="form-group">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" name="password" id="password" class="form-input" required>
                </div>
                <div class="form-group">
                    <button type="submit" class="form-submit">Login</button>
                </div>
                <f:table collection="${bikeList}" />
            </form>
        </div>
    </div>
</body>
</html>

<html>  
<body>
    <script type="text/javascript">

    function submitAction(act) 
    {
         document.sample.action = act;
         document.sample.submit();

    }
    </script>
    <h1>ADMIN</h1>
    <form name ="sample" action="index.php">
        <input type="button" value = "Register" onClick="submitAction('AdminRegister.php')">
        </br>
        <input type="button" value = "Login" onClick="submitAction('AdminLogin.php')">
        </br>
    </form>
</body>
</html>
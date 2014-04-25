<html>  
<body>
    <script type="text/javascript">

    function submitAction(act) 
    {
         document.sample.action = act;
         document.sample.submit();

    }
    </script>
    <h1>USER</h1>
    <form name ="sample" action="index.php">
        <input type="button" value = "Register" onClick="submitAction('Register.php')">
        </br>
        <input type="button" value = "Login" onClick="submitAction('Login.php')">
        </br>
    </form>
</body>
</html>
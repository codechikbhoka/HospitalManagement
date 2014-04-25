<html>  
<body>
    <script type="text/javascript">
    function submitAction(act) 
    {
         document.sample.action = act;
         document.sample.submit();

    }
    </script>
    <h1>HOSPITAL MANAGEMENT</h1>
    <form name ="sample" action="index.php">
        <select>
          <option value="Admin">Admin</option>
          <option value="User">User</option>
          <option value="Doctor">Doctor</option>
          <option value="Nurse">Nurse</option>
          <option value="HouseKeeping">HouseKeeping</option>
        </select>  
    <input type="button" value = "Submit" onClick="submitAction('Admin.php')">
    </br>
    </form>
</body>
</html>
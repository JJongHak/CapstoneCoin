<?php
  $connect = @mysql_connect("localhost","root","capstone") or die("error");
  $dbname = "android_test(capstone)";
  $dbconn = mysql_select_db($dbname,$connect);
  $username = $_POST['username'];

    $query_search = "select * from user where id = '".$username."' AND pw = '".$password. "'";
    $password = $_POST['password'];
    $query_exec = mysql_query($query_search) or die(mysql_error());
        echo "User Found";
    $rows = mysql_num_rows($query_exec);

    if($rows == 0) {
        echo "No Such User Found";
    }
    else  {
    }
?>
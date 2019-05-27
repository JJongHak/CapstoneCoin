<?php

	$con=mysqli_connect("localhost","root", "","capstone");

	if (mysqli_connect_errno($con))
	{
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	mysqli_set_charset($con,"utf8");
	$res = mysqli_query($con,"select * from google_table");
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title> 구글 기업 공지사항 </title>
</head>
<body>
	<?php
		while($row = mysqli_fetch_array($res)){
			echo $row['title'];
			echo "\t";
			echo $row['classify'];
			echo "\t";
			echo $row['address'];
			echo "\t";
			echo $row['url'];
			echo "<br/>";
		}

	?>
</body>
</html>


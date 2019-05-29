<?php

$con=mysqli_connect("localhost","root","","capstone");

if (mysqli_connect_errno($con))
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

mysqli_set_charset($con,"utf8");

$res = mysqli_query($con,"select * from google_table");

$result = array();

while($row = mysqli_fetch_array($res)){
	array_push($result,array('title'=>$row[0],'classify'=>$row[1],'address'=>$row[2], 'url'=>$row[3]));
}

echo json_encode(array("result"=>$result));

mysqli_close($con);

?>


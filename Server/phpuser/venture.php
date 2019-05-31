<?php

$con=mysqli_connect("localhost","root","","capstone");

if (mysqli_connect_errno($con))
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

mysqli_set_charset($con,"utf8");

$res = mysqli_query($con,"select * from venture");

$result = array();

while($row = mysqli_fetch_array($res)){
	array_push($result,array('classify'=>$row[0],'c_name'=>$row[1],'title'=>$row[2], 's_date'=>$row[3], 'e_date'=>$row[4]));
}

echo json_encode(array("result"=>$result));

mysqli_close($con);

?>
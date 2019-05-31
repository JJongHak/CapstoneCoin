<?php

$con=mysqli_connect("localhost","root","","capstone");

if (mysqli_connect_errno($con))
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

mysqli_set_charset($con,"utf8");

$res = mysqli_query($con,"select * from company");

$result = array();

while($row = mysqli_fetch_array($res)){
	array_push($result,array('serial'=>$row[0],'c_name'=>$row[1],'type'=>$row[2], 'people_num'=>$row[3], 'avg_money'=>$row[4]));
}

echo json_encode(array("result"=>$result));

mysqli_close($con);

?>
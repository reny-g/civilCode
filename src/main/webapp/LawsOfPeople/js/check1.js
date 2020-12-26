function check(form){
var username = form.username.value;
var password = form.password.value;
if(username.length>15||username.length<6){
alert("用户名长度应为6到15位！");
form.username.focus();
}
if(!/^[a-zA-z]/.test(username))
{
	alert("用户名应以字母开头！");
	form.username.focus();
}
else if(password.length>12||password.length<6){
	alert("密码长度应为6到12位！");
	form.password.focus();
}
else{
	return true;
}
}

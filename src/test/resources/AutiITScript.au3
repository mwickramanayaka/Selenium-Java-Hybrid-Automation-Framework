#cs
focus on an object element
set the objct with the location
click
#ce
ControlFocus("Open","","Edit1")
Sleep(3000)
ControlSetText("Open","","Edit1","D:\seleniumTests\inprogress\AutoIT\image.jpg")
Sleep(3000)
ControlClick("Open","","Button1")
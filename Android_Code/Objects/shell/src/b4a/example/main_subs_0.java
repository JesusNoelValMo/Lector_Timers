package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,37);
if (RapidSub.canDelegate("activity_create")) return main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);
RemoteObject _dev = RemoteObject.createImmutable(0);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 37;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(16);
 BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"Main_Layout\")";
Debug.ShouldStop(32768);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Main_Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 49;BA.debugLine="If (usb.HasPermission(1)) Then	' Ver_2.4";
Debug.ShouldStop(65536);
if ((main._usb.runMethod(true,"HasPermission",(Object)(BA.numberCast(int.class, 1)))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 51;BA.debugLine="Dim dev As Int";
Debug.ShouldStop(262144);
_dev = RemoteObject.createImmutable(0);Debug.locals.put("dev", _dev);
 BA.debugLineNum = 54;BA.debugLine="dev = usb.Open(115200, 1)		' Ver_2.4";
Debug.ShouldStop(2097152);
_dev = main._usb.runMethod(true,"Open",main.processBA,(Object)(BA.numberCast(int.class, 115200)),(Object)(BA.numberCast(int.class, 1)));Debug.locals.put("dev", _dev);
 BA.debugLineNum = 58;BA.debugLine="If dev <> usb.USB_NONE Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("!",_dev,BA.numberCast(double.class, main._usb.getField(true,"USB_NONE")))) { 
 BA.debugLineNum = 63;BA.debugLine="astreams.Initialize(usb.GetInputStream,usb.Get";
Debug.ShouldStop(1073741824);
main._astreams.runVoidMethod ("Initialize",main.processBA,(Object)(main._usb.runMethod(false,"GetInputStream")),(Object)(main._usb.runMethod(false,"GetOutputStream")),(Object)(RemoteObject.createImmutable("Astreams")));
 }else {
 BA.debugLineNum = 65;BA.debugLine="Log(\"Error opening USB port 1\")";
Debug.ShouldStop(1);
main.mostCurrent.__c.runVoidMethod ("Log",(Object)(RemoteObject.createImmutable("Error opening USB port 1")));
 };
 }else {
 BA.debugLineNum = 68;BA.debugLine="usb.RequestPermission(1)  ' Ver_2.4";
Debug.ShouldStop(8);
main._usb.runVoidMethod ("RequestPermission",(Object)(BA.numberCast(int.class, 1)));
 };
 BA.debugLineNum = 71;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,82);
if (RapidSub.canDelegate("activity_pause")) return main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 82;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 84;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,78);
if (RapidSub.canDelegate("activity_resume")) return main.remoteMe.runUserSub(false, "main","activity_resume");
 BA.debugLineNum = 78;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8192);
 BA.debugLineNum = 80;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _astreams_newdata(RemoteObject _buffer) throws Exception{
try {
		Debug.PushSubsStack("Astreams_NewData (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,99);
if (RapidSub.canDelegate("astreams_newdata")) return main.remoteMe.runUserSub(false, "main","astreams_newdata", _buffer);
RemoteObject _command = RemoteObject.createImmutable("");
RemoteObject _pos_box = RemoteObject.createImmutable(0);
RemoteObject _bandera = RemoteObject.createImmutable(false);
Debug.locals.put("Buffer", _buffer);
 BA.debugLineNum = 99;BA.debugLine="Sub Astreams_NewData (Buffer() As Byte)";
Debug.ShouldStop(4);
 BA.debugLineNum = 100;BA.debugLine="Dim command As String";
Debug.ShouldStop(8);
_command = RemoteObject.createImmutable("");Debug.locals.put("command", _command);
 BA.debugLineNum = 101;BA.debugLine="Dim Pos_Box As Int";
Debug.ShouldStop(16);
_pos_box = RemoteObject.createImmutable(0);Debug.locals.put("Pos_Box", _pos_box);
 BA.debugLineNum = 102;BA.debugLine="Dim bandera As Boolean";
Debug.ShouldStop(32);
_bandera = RemoteObject.createImmutable(false);Debug.locals.put("bandera", _bandera);
 BA.debugLineNum = 104;BA.debugLine="command =conv.StringFromBytes(Buffer, \"UTF8\")";
Debug.ShouldStop(128);
_command = main._conv.runMethod(true,"StringFromBytes",(Object)(_buffer),(Object)(RemoteObject.createImmutable("UTF8")));Debug.locals.put("command", _command);
 BA.debugLineNum = 105;BA.debugLine="If command.Contains(\"ID0\") Then";
Debug.ShouldStop(256);
if (_command.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ID0"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 106;BA.debugLine="Msgbox(command.IndexOf(\"ID0\"),\"999\")";
Debug.ShouldStop(512);
main.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.NumberToString(_command.runMethod(true,"indexOf",(Object)(RemoteObject.createImmutable("ID0"))))),(Object)(RemoteObject.createImmutable("999")),main.mostCurrent.activityBA);
 BA.debugLineNum = 107;BA.debugLine="Timer_1.Text = command";
Debug.ShouldStop(1024);
main.mostCurrent._timer_1.runMethod(true,"setText",(_command));
 };
 BA.debugLineNum = 110;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,94);
if (RapidSub.canDelegate("button1_click")) return main.remoteMe.runUserSub(false, "main","button1_click");
 BA.debugLineNum = 94;BA.debugLine="Sub Button1_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 96;BA.debugLine="mensaje_Out=\"a\"";
Debug.ShouldStop(-2147483648);
main._mensaje_out = BA.ObjectToString("a");
 BA.debugLineNum = 97;BA.debugLine="Write_In_Arduino(mensaje_Out)";
Debug.ShouldStop(1);
_write_in_arduino(main._mensaje_out);
 BA.debugLineNum = 98;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 30;BA.debugLine="Dim Timer_1, Timer_2, Timer_3, Timer_4, Timer_5,";
main.mostCurrent._timer_1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_11 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_12 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_13 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_14 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Dim Timer_15, Timer_16, Timer_17, Timer_18, Timer";
main.mostCurrent._timer_15 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_16 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_17 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_18 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_19 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_20 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_21 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
main.mostCurrent._timer_22 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Dim EditText1 As EditText";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _label_click() throws Exception{
try {
		Debug.PushSubsStack("Label_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,87);
if (RapidSub.canDelegate("label_click")) return main.remoteMe.runUserSub(false, "main","label_click");
RemoteObject _lbl_tag = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
 BA.debugLineNum = 87;BA.debugLine="Sub Label_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 88;BA.debugLine="Dim lbl_tag As Label";
Debug.ShouldStop(8388608);
_lbl_tag = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl_tag", _lbl_tag);
 BA.debugLineNum = 89;BA.debugLine="lbl_tag = Sender";
Debug.ShouldStop(16777216);
_lbl_tag.setObject(main.mostCurrent.__c.runMethod(false,"Sender",main.mostCurrent.activityBA));
 BA.debugLineNum = 90;BA.debugLine="lbl_tag.Text = lbl_tag.Tag";
Debug.ShouldStop(33554432);
_lbl_tag.runMethod(true,"setText",_lbl_tag.runMethod(false,"getTag"));
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim conv As ByteConverter";
main._conv = RemoteObject.createNew ("anywheresoftware.b4a.agraham.byteconverter.ByteConverter");
 //BA.debugLineNum = 19;BA.debugLine="Dim usb As UsbSerial";
main._usb = RemoteObject.createNew ("anywheresoftware.b4a.objects.UsbSerial");
 //BA.debugLineNum = 20;BA.debugLine="Dim astreams As AsyncStreams";
main._astreams = RemoteObject.createNew ("anywheresoftware.b4a.randomaccessfile.AsyncStreams");
 //BA.debugLineNum = 21;BA.debugLine="Dim mensaje_Out As String";
main._mensaje_out = RemoteObject.createImmutable("");
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _write_in_arduino(RemoteObject _ms) throws Exception{
try {
		Debug.PushSubsStack("Write_In_Arduino (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,73);
if (RapidSub.canDelegate("write_in_arduino")) return main.remoteMe.runUserSub(false, "main","write_in_arduino", _ms);
Debug.locals.put("Ms", _ms);
 BA.debugLineNum = 73;BA.debugLine="Sub Write_In_Arduino (Ms As String)";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="Ms = mensaje_Out";
Debug.ShouldStop(512);
_ms = main._mensaje_out;Debug.locals.put("Ms", _ms);
 BA.debugLineNum = 75;BA.debugLine="astreams.Write(Ms.GetBytes(\"UTF8\"))";
Debug.ShouldStop(1024);
main._astreams.runVoidMethod ("Write",(Object)(_ms.runMethod(false,"getBytes",(Object)(RemoteObject.createImmutable("UTF8")))));
 BA.debugLineNum = 76;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
}